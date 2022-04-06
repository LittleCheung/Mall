package com.mall.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.member.entity.MemberLevelEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 *
 * @author yaoxinjia
 * @email 894548575@qq.com
 * @date 2021-02-09 20:58:11
 */
@Mapper
public interface MemberLevelDao extends BaseMapper<MemberLevelEntity> {

    MemberLevelEntity getDefaultLevel();
}
