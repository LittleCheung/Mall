package com.mall.auth.controller;

import com.alibaba.fastjson.TypeReference;

import com.mall.auth.feign.MemberFeignService;
import com.mall.auth.vo.UserLoginVo;
import com.mall.auth.feign.ThirdPartyFeignService;
import com.mall.auth.vo.UserRegisterVo;
import com.mall.common.constant.AuthServerConstant;
import com.mall.common.exception.BizCodeEnum;
import com.mall.common.utils.R;
import com.mall.common.vo.MemberRespVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 处理手机号登录相关请求
 * @author littlecheung
 */
@Controller
public class LoginController {

    @Autowired
    private ThirdPartyFeignService feignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MemberFeignService memberFeignService;

    /**
     * 发送手机登录验证码
     * @param phone 手机号
     * @return
     */
    @GetMapping("/sms/sendCode")
    @ResponseBody
    public R sendCode(@RequestParam("phone") String phone){
        //1 防止接口被恶意刷验证码
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (StringUtils.isNotBlank(redisCode)){
            //redis存储时间戳
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - l < 60000){
                //如果1分钟内已给这个手机号发过短信就不能再发
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(),BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }

        //2 验证码再次校验，之后存到redis，key=phone，value=code+时间戳
        String code = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
        String redisValue = code +"_"+System.currentTimeMillis();
        //redis缓存验证码，防止同一个手机号再次发送验证码
        redisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX+phone,redisValue,10, TimeUnit.MINUTES);
        feignService.sendCode(phone,code);
        return R.ok();
    }

    /**
     * 使用手机号注册
     * @param vo 用户注册实体
     * @param result 利用session原理，将数据放在session中，只要跳到下一个页面取出这个数据以后，session里面的数据就会被删掉
     * @param redirectAttributes: 模拟重定向携带数据
     * @return
     */
    @PostMapping("/register")
    public String register(@Valid UserRegisterVo vo, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            //校验出错，转发到注册页
            Map<String, String> map = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            redirectAttributes.addFlashAttribute("errors",map);
            return "redirect:http://auth.mall.com/reg.html";
        }

        //1 校验验证码
        String code = vo.getCode();
        String s = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (StringUtils.isNotBlank(s)){
            if (code.equals(s.split("_")[0])){
                //删除验证码;令牌机制
                redisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                //验证码校验通过后，真正调用第三方服务远程服务注册
                R r = memberFeignService.regist(vo);
                if (r.getCode()==0){
                    //成功就转到登录页
                    return "redirect:http://auth.mall.com/login.html";
                }else{
                    Map<String,Object> map = new HashMap<>();
                    map.put("msg",r.get("msg"));
                    System.out.println(map);
                    redirectAttributes.addFlashAttribute("errors",map);
                    return "redirect:http://auth.mall.com/reg.html";
                }
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("code","验证码输入错误");
                redirectAttributes.addFlashAttribute("errors",map);
                //校验出错，转发到注册页
                return "redirect:http://auth.mall.com/reg.html";
            }
        }else{
            Map<String,Object> map = new HashMap<>();
            map.put("code","验证码输入错误");
            redirectAttributes.addFlashAttribute("errors",map);
            //校验出错，转发到注册页
            return "redirect:http://auth.mall.com/reg.html";
        }
    }

    /**
     * 使用手机号登录
     * @param vo 用户登录实体
     * @param redirectAttributes 模拟重定向携带数据
     * @param session 利用session原理，将数据放在session中，只要跳到下一个页面取出这个数据以后，session里面的数据就会被删掉
     * @return
     */
    @PostMapping("/login")
    public String login(UserLoginVo vo, RedirectAttributes redirectAttributes, HttpSession session){
        //远程登录
        R r = memberFeignService.login(vo);
        if (r.getCode()==0){
            MemberRespVo data = r.getData(new TypeReference<MemberRespVo>() {
            });
            session.setAttribute(AuthServerConstant.SESSION_LOGIN_KEY,data);
            return "redirect:http://mall.com";
        }else{
            Map<String,String> errors = new HashMap<>();
            errors.put("msg",r.getData("msg",new TypeReference<String>(){}));
            redirectAttributes.addFlashAttribute("errors",errors);
            return "redirect:http://auth.mall.com/login.html";
        }
    }

    /**
     * 处理页面跳转请求
     * @param session
     * @return
     */
    @GetMapping("/login.html")
    public String loginPage(HttpSession session){
        Object attribute = session.getAttribute(AuthServerConstant.SESSION_LOGIN_KEY);
        if (attribute == null){
            //没登录去登录页
            return "login";
        }else {
            //已登录去首页
            return "redirect:http://mall.com";
        }
    }

}
