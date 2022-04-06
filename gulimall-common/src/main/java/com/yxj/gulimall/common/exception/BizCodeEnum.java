package com.yxj.gulimall.common.exception;

/**
 *
 * @author yaoxinjia
 */
public enum BizCodeEnum {
    UNKNOWN_EXCEPTION(10000,"系统未知错误"),
    VALID_EXCEPTION(10001,"参数校验异常"),
    SMS_CODE_EXCEPTION(10002,"验证码获取频率太高，请稍后再试"),
    TO_MANY_REQUEST(10003,"请求流量过大，请稍后再试"),
    PRODUCT_UP_EXCEPTION(11000,"商品上架异常"),
    USER_EXISTS_EXCEPTION(15001,"用户名已存在"),
    PHONE_EXISTS_EXCEPTION(15002,"手机号已存在"),
    NO_STOCK_EXCEPTION(21000,"商品库存不足"),
    LOGIN_ACCOUNT_PASSWORD_INVALID(15003,"用户名或密码错误");


    private Integer code;
    private String msg;
    private BizCodeEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
