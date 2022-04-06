package com.yxj.gulimall.member.exception;
/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
public class UsernameException extends RuntimeException {


    public UsernameException() {
        super("存在相同的用户名");
    }
}
