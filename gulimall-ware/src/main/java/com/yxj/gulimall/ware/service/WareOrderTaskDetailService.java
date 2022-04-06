package com.yxj.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxj.gulimall.common.utils.PageUtils;
import com.yxj.gulimall.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;
/**
 * @author yaoxinjia
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

