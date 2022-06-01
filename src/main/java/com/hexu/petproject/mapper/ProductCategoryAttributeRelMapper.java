package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.ProductCategoryAttributeRel;

import java.util.List;

/**
 * @author 86176
 */
public interface ProductCategoryAttributeRelMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductCategoryAttributeRel record);

    ProductCategoryAttributeRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductCategoryAttributeRel record);

    /**
     * 根据数组Id查询出分类关联的属性Id
     * @param ids 分类数组Id
     * @return 属性Id集合
     */
    List<Long> listIds(Integer[] ids);
}