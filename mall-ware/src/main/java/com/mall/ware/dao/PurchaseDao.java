package com.mall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.ware.entity.PurchaseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息
 *
 * @author littlecheung
 */
@Mapper
public interface PurchaseDao extends BaseMapper<PurchaseEntity> {
	
}
