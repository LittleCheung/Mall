package com.mall.order.controller;

import java.util.Arrays;
import java.util.Map;

import com.mall.common.utils.PageUtils;
import com.mall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mall.order.entity.OrderEntity;
import com.mall.order.service.OrderService;


/**
 * 处理订单请求
 * @author littlecheung
 */
@RestController
@RequestMapping("order/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 列表分页查询
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = orderService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 分页查询当前登录用户的所有订单
     * @param params
     * @return
     */
    @GetMapping("/listWithItem")
    public R listWithItem(@RequestBody Map<String, Object> params){

        PageUtils page = orderService.queryPageWithItem(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		OrderEntity order = orderService.getById(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrderEntity order){
		orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
