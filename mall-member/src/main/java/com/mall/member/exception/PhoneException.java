package com.mall.member.exception;

/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
public class PhoneException extends RuntimeException {

    public PhoneException() {
        super("存在相同的手机号");
    }
}
