package com.mall.cart.to;

import lombok.Data;


/**
 * 用户信息实体类
 * @author littlecheung
 */
@Data
public class UserInfoTo {

    private Long userId;

    private String userKey;

    /**
     * 是否临时用户
     */
    private Boolean tempUser = false;

}
