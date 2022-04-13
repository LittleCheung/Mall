package com.mall.cart.to;

import lombok.Data;


/**
 * 用户信息实体类
 * @author littlecheung
 */
@Data
public class UserInfoTo {

    /**
     * 用户唯一标识
     */
    private Long userId;
    /**
     * 浏览器端用户身份标识
     */
    private String userKey;
    /**
     * 默认设置为不是临时用户
     */
    private Boolean tempUser = false;

}
