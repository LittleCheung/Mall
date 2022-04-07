package com.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.coupon.entity.HomeSubjectEntity;
import com.mall.common.utils.PageUtils;

import java.util.Map;

/**
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
 * @author littlecheung
 */
public interface HomeSubjectService extends IService<HomeSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

