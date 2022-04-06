package com.mall.coupon.dao;

import com.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author yaoxinjia
 * @email 894548575@qq.com
 * @date 2021-02-09 20:18:04
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
