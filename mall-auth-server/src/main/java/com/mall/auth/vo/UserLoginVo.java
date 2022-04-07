package com.mall.auth.vo;

import lombok.Data;

/**
 * 用户登录实体
 * @author littlecheung
 */
@Data
public class UserLoginVo {

    private String loginAccount;

    private String passWord;
}
