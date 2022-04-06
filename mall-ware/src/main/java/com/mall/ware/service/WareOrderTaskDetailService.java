package com.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.ware.entity.WareOrderTaskDetailEntity;
import com.yxj.gulimall.common.utils.PageUtils;

import java.util.Map;
/**
 * @author yaoxinjia
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

