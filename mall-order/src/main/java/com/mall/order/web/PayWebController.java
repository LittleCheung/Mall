package com.mall.order.web;

import com.alipay.api.AlipayApiException;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.mall.order.config.AlipayTemplate;
import com.mall.order.entity.OrderEntity;
import com.mall.order.service.OrderService;
import com.mall.order.vo.PayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.lly835.bestpay.enums.BestPayTypeEnum.WXPAY_NATIVE;

/**
 * 处理支付请求
 * @author littlecheung
 */
@Slf4j
@Controller
public class PayWebController {

    @Autowired
    private AlipayTemplate alipayTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BestPayService bestPayService;

    @Resource
    private WxPayConfig wxPayConfig;

    /**
     * 支付宝支付
     * @param orderSn
     * @return
     * @throws AlipayApiException
     */
    @ResponseBody
    @GetMapping(value = "/aliPayOrder",produces = "text/html")
    public String aliPayOrder(@RequestParam("orderSn") String orderSn) throws AlipayApiException {

        PayVo payVo = orderService.getOrderPay(orderSn);
        String pay = alipayTemplate.pay(payVo);
        System.out.println(pay);
        return pay;
    }


    /**
     * 微信支付
     * @param orderSn
     * @return
     */
    @GetMapping(value = "/weixinPayOrder")
    public String weixinPayOrder(@RequestParam("orderSn") String orderSn, Model model) {

        OrderEntity orderInfo = orderService.getOrderByOrderSn(orderSn);

        if (orderInfo == null) {
            throw new RuntimeException("订单不存在");
        }

        PayRequest request = new PayRequest();
        request.setOrderName("4559066-最好的支付sdk");
        request.setOrderId(orderInfo.getOrderSn());
        request.setOrderAmount(0.01);
        request.setPayTypeEnum(WXPAY_NATIVE);

        PayResponse payResponse = bestPayService.pay(request);
        payResponse.setOrderId(orderInfo.getOrderSn());
        log.info("发起支付 response={}", payResponse);

        //传入前台的二维码路径生成支付二维码
        model.addAttribute("codeUrl",payResponse.getCodeUrl());
        model.addAttribute("orderId",payResponse.getOrderId());
        model.addAttribute("returnUrl",wxPayConfig.getReturnUrl());

        return "createForWxNative";
    }


    //根据订单号查询订单状态的API
    @GetMapping(value = "/queryByOrderId")
    @ResponseBody
    public OrderEntity queryByOrderId(@RequestParam("orderId") String orderId) {
        log.info("查询支付记录...");
        return orderService.getOrderByOrderSn(orderId);
    }
}
