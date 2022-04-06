package com.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxj.gulimall.common.utils.PageUtils;
import com.mall.member.entity.MemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author yaoxinjia
 * @email 894548575@qq.com
 * @date 2021-02-09 20:58:11
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

