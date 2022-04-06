package com.mall.member.exception;
/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
public class UserNameExistException extends RuntimeException{

    public UserNameExistException() {
        super("用户名已存在");
    }
}
