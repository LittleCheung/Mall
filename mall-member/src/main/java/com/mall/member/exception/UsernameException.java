package com.mall.member.exception;
/**
 * 相同用户名异常处理
 * @author littlecheung
 */
public class UsernameException extends RuntimeException {


    public UsernameException() {
        super("存在相同的用户名");
    }
}
