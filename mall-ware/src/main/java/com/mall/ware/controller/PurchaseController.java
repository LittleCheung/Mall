package com.mall.ware.controller;

import com.mall.ware.entity.PurchaseEntity;
import com.mall.ware.service.PurchaseService;
import com.mall.ware.vo.MergeVo;
import com.mall.ware.vo.PurchaseDoneVo;
import com.mall.common.utils.PageUtils;
import com.mall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 采购单相关功能
 * @author littlecheung
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    /**
     * 完成采购单
     * @param doneVo
     * @return
     */
    @PostMapping(value = "/done")
    public R finish(@RequestBody PurchaseDoneVo doneVo) {

        purchaseService.done(doneVo);
        return R.ok();
    }

    /**
     * 领取采购单
     * @param ids
     * @return
     */
    @PostMapping(value = "/received")
    public R received(@RequestBody List<Long> ids) {

        purchaseService.received(ids);
        return R.ok();
    }

    /**
     * 合并采购单
     * @param mergeVo
     * @return
     */
    @PostMapping(value = "/merge")
    public R merge(@RequestBody MergeVo mergeVo) {

        purchaseService.mergePurchase(mergeVo);
        return R.ok();
    }


    @GetMapping(value = "/unreceive/list")
    public R unreceiveList(@RequestParam Map<String, Object> params){

        PageUtils page = purchaseService.queryPageUnreceive(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = purchaseService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){

		PurchaseEntity purchase = purchaseService.getById(id);
        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PurchaseEntity purchase){

        purchase.setCreateTime(new Date());
        purchase.setUpdateTime(new Date());
		purchaseService.save(purchase);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PurchaseEntity purchase){

		purchaseService.updateById(purchase);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){

		purchaseService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
