package com.yxj.gulimall.product.service.impl;

import com.yxj.gulimall.common.utils.PageUtils;
import com.yxj.gulimall.common.utils.Query;
import com.yxj.gulimall.product.entity.AttrEntity;
import com.yxj.gulimall.product.service.AttrService;
import com.yxj.gulimall.product.vo.AttrGroupWithAttrsVo;

import com.yxj.gulimall.product.vo.SpuItemAttrGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yxj.gulimall.product.dao.AttrGroupDao;
import com.yxj.gulimall.product.entity.AttrGroupEntity;
import com.yxj.gulimall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;

/**
 * @author yaoxinjia
 */
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrService attrService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        // select * from pms_attr_group where catelog_id=? and (attr_group_id=key or attr_group_name like  %key%)
        String key = (String) params.get("key");
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((obj) -> {
                obj.eq("attr_group_id", key).or().like("attr_group_name", key);


            });
        }
        if (catelogId == 0) {
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        } else {

            wrapper.eq("catelog_id", catelogId);
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
            return new PageUtils(page);

        }
    }


    /**
     * 根据分类id查出所有分组以及这些组里面的属性
     * bug已经解决
     * @param catelogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {

        // 1 查出分组信息
        List<AttrGroupEntity> attrGroupEntities = this.list(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));

        // 2 查询所有属性
        List<AttrGroupWithAttrsVo> collect = attrGroupEntities.stream().map((group) -> {
            AttrGroupWithAttrsVo attrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(group,attrsVo);
            List<AttrEntity> attrs = attrService.getRelationAttr(attrsVo.getAttrGroupId());
            attrsVo.setAttrs(attrs);
            return  attrsVo;
        }).collect(Collectors.toList());
        List<AttrGroupWithAttrsVo> collect2 = collect.stream().filter((item) -> {
            if (item.getAttrs() == null) {
                return true;
            } else {
                List<AttrEntity> collect1 = item.getAttrs().stream().filter((u) -> {
                    return u.getAttrType() == 1;
                }).collect(Collectors.toList());
                item.setAttrs(collect1);
                return true;
            }
        }).collect(Collectors.toList());
        return collect2;
    }

    @Override
    public List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
        //查询当前spu对应的所有属性的分组信息以及当前分组下所有属性对应的值
        List<SpuItemAttrGroupVo> vos = this.baseMapper.getAttrGroupWithAttrsBySpuId(spuId,catalogId);
        return vos;
    }


}