package com.mall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.ware.entity.WareInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 *
 * @author littlecheung
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {
	
}
