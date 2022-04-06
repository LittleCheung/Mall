package com.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxj.gulimall.common.utils.PageUtils;
import com.mall.member.entity.MemberStatisticsInfoEntity;

import java.util.Map;

/**
 * @author yaoxinjia
 * @email 894548575@qq.com
 */
public interface MemberStatisticsInfoService extends IService<MemberStatisticsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

