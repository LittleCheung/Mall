package com.mall.member.vo;

import lombok.Data;

/**
 * 社交用户实体
 * @author littlecheung
 */
@Data
public class SocialUser {

    private String access_token;

    private String remind_in;

    private long expires_in;

    private String uid;

    private String isRealName;

}
