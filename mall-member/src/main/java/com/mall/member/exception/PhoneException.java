package com.mall.member.exception;

/**
 * 手机号相关异常处理
 * @author littlecheung
 */
public class PhoneException extends RuntimeException {

    public PhoneException() {
        super("存在相同的手机号");
    }
}
