package com.mall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.ware.entity.WareOrderTaskEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库订单任务
 *
 * @author littlecheung
 */
@Mapper
public interface WareOrderTaskDao extends BaseMapper<WareOrderTaskEntity> {
	
}
