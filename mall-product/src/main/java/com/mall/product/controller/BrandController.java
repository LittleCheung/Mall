package com.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mall.product.entity.BrandEntity;
import com.mall.product.service.BrandService;
import com.yxj.gulimall.common.utils.PageUtils;
import com.yxj.gulimall.common.utils.R;
import com.yxj.gulimall.common.valid.AddGroup;
import com.yxj.gulimall.common.valid.UpdateGroup;
import com.yxj.gulimall.common.valid.UpdateStatusGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 品牌信息
 * @author littlecheung
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 输出品牌列表
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {

        PageUtils page = brandService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 获取品牌信息
     * @param brandId
     * @return
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId) {

        BrandEntity brand = brandService.getById(brandId);
        return R.ok().put("brand", brand);
    }

    /**
     * 根据id获取品牌信息
     * @param brandIds
     * @return
     */
    @GetMapping("/infos")
    public R info(@RequestParam("brandIds")List<Long> brandIds) {

       List<BrandEntity> brand = brandService.getBrandsByIds(brandIds);
        return R.ok().put("brand", brand);
    }

    /**
     * 保存品牌
     * @param brand
     * @return
     */
    @RequestMapping("/save")
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand) {

        brandService.save(brand);
        return R.ok();
    }


    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    @RequestMapping("/update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {

        brandService.updateDetail(brand);
        return R.ok();
    }


    /**
     * 修改状态
     * @param brand
     * @return
     */
    @RequestMapping("/update/status")
    public R updateStatus(@Validated({UpdateStatusGroup.class}) @RequestBody BrandEntity brand) {

        brandService.updateById(brand);
        return R.ok();
    }

    /**
     * 删除
     * @param brandIds
     * @return
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds) {

        brandService.removeByIds(Arrays.asList(brandIds));
        return R.ok();
    }

}
