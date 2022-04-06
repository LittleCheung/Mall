package com.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.ware.entity.WareInfoEntity;
import com.yxj.gulimall.common.utils.PageUtils;
import com.mall.ware.vo.FareVo;

import java.util.Map;

/**
 * @author yaoxinjia
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取运费和收货地址信息
     * @param addrId
     * @return
     */
    FareVo getFare(Long addrId);
}

