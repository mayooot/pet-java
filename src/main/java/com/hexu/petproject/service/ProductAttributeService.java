package com.hexu.petproject.service;

import com.hexu.petproject.model.vo.attributeVO.AttributeTreeVO;
import com.hexu.petproject.model.vo.attributeVO.ProductAttributeVO;

import java.util.List;

/**
 * @Classname ProductAttributeService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-04 13:41
 * @Version 1.0
 **/
public interface ProductAttributeService {
    /**
     * 根据商品二级分类ID查询商品属性列表接口
     * @param ids 分类数组
     * @return
     */
    List<AttributeTreeVO> byCategoryIds(Integer[] ids);

    /**
     * 查询所有商品属性列表接口
     * @return
     */
    List<AttributeTreeVO> list();

    /**
     * 根据商品spuID查询商品属性
     * @param spuId
     * @return
     */
    List<ProductAttributeVO> getAttributeVOBySpuId(Long spuId);
}
