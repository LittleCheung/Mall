package com.mall.member.exception;

/**
 * 用户名已存在异常处理
 * @author littlecheung
 */
public class UserNameExistException extends RuntimeException{

    public UserNameExistException() {
        super("用户名已存在");
    }
}
