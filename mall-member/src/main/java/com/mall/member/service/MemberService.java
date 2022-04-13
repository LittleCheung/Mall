package com.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.member.vo.SocialUser;
import com.mall.common.utils.PageUtils;
import com.mall.member.entity.MemberEntity;
import com.mall.member.exception.PhoneException;
import com.mall.member.exception.UsernameException;
import com.mall.member.vo.MemberUserLoginVo;
import com.mall.member.vo.MemberUserRegisterVo;

import java.util.Map;

/**
 * 会员相关功能
 * @author littleCheung
 */
public interface MemberService extends IService<MemberEntity> {

    /**
     * 用户注册
     * @param vo
     */
    void register(MemberUserRegisterVo vo);

    /**
     * 判断手机号是否重复
     * @param phone
     * @return
     */
    void checkPhoneUnique(String phone) throws PhoneException;

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    void checkUserNameUnique(String userName) throws UsernameException;

    /**
     * 用户登录
     * @param vo
     * @return
     */
    MemberEntity login(MemberUserLoginVo vo);

    /**
     * 社交用户登录
     * @param socialUser
     * @return
     * @throws Exception
     */
    MemberEntity oauth2login(SocialUser socialUser) throws Exception;

    /**
     * 微信登录
     * @param accessTokenInfo
     * @return
     */
    MemberEntity wxlogin(String accessTokenInfo);


    PageUtils queryPage(Map<String, Object> params);
}

