package com.yxj.gulimall.product.app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.yxj.gulimall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxj.gulimall.product.entity.CategoryEntity;
import com.yxj.gulimall.product.service.CategoryService;



/**
 * 商品三级分类
 *
 * @author yaoxinjia
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查出所有分类以及子分类，以树形结构组装起来
     * @param params
     * @return
     */
    @RequestMapping("/list/tree")
    public R list(@RequestParam Map<String, Object> params){
       List<CategoryEntity> entities = categoryService.listWithTree();

        return R.ok().put("data", entities);
    }


    /**
     * 信息
     * @param catId
     * @return
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("data", category);
    }


    /**
     * 保存
     * @param category
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }


    /**
     * 修改分类
     * @param category
     * @return
     */
    @RequestMapping("/update/sort")
    public R update(@RequestBody CategoryEntity[] category){

        categoryService.updateBatchById(Arrays.asList(category));
        return R.ok();
    }

    /**
     * 修改
     * @param category
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateCasecade(category);

        return R.ok();
    }

    /**
     * 删除
     * @RequestBody获取请求体，必须发送POST请求
     * SpringMVC自动将请求体的数据(json),转为对应的对象
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){

        categoryService.removeMenuByIds(Arrays.asList(catIds));
        return R.ok();
    }

}
