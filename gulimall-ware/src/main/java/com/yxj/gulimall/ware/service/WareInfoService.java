package com.yxj.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxj.gulimall.common.utils.PageUtils;
import com.yxj.gulimall.ware.entity.WareInfoEntity;
import com.yxj.gulimall.ware.vo.FareVo;

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

