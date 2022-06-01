package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.ProductAttribute;
import com.hexu.petproject.model.vo.attributeVO.AttributeTreeVO;
import com.hexu.petproject.model.vo.attributeVO.ProductAttributeVO;

import java.util.List;

public interface ProductAttributeMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductAttribute record);

    ProductAttribute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductAttribute record);

    /**
     * 查询属性分类
     * @return 属性模型
     */
    List<ProductAttribute> listTree();

    /**
     * 根据商品spuID查询商品属性
     * @param spuId
     * @return
     */
    List<ProductAttributeVO> selectAttributeVOBySpuId(Long spuId);


}