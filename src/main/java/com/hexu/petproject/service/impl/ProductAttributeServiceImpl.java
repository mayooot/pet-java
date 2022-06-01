package com.hexu.petproject.service.impl;

import com.hexu.petproject.mapper.ProductAttributeMapper;
import com.hexu.petproject.mapper.ProductCategoryAttributeRelMapper;
import com.hexu.petproject.model.pojo.ProductAttribute;
import com.hexu.petproject.model.vo.attributeVO.AttributeTreeVO;
import com.hexu.petproject.model.vo.attributeVO.ProductAttributeVO;
import com.hexu.petproject.model.vo.attributeVO.ProductAttributeValueDtoListVO;
import com.hexu.petproject.service.ProductAttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname ProductAttributeServiceImpl
 * @Description 属性业务层
 * @Author 86176
 * @Date 2022-04-04 13:42
 * @Version 1.0
 **/
@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

    @Resource
    private ProductAttributeMapper productAttributeMapper;
    @Resource
    private ProductCategoryAttributeRelMapper productCategoryAttributeRelMapper;

    @Override
    public List<AttributeTreeVO> byCategoryIds(Integer[] ids) {
        //根据前端传过来的额分类数组查询出属性Id集合
        List<Long> integers = productCategoryAttributeRelMapper.listIds(ids);
        System.out.println("---------------------" + integers);
        return this.listTreeCommon().stream().filter(x -> integers.contains(x.getKeyId())).collect(Collectors.toList());
    }

    @Override
    public List<AttributeTreeVO> list() {
        return this.listTreeCommon();
    }

    @Override
    public List<ProductAttributeVO> getAttributeVOBySpuId(Long spuId) {
        return productAttributeMapper.selectAttributeVOBySpuId(spuId);
    }

    /**
     * 公共部分
     *
     * @return 公共AttributeTreeVO
     */
    public List<AttributeTreeVO> listTreeCommon() {
        List<ProductAttribute> list = productAttributeMapper.listTree();

        return list.stream().filter(id -> id.getParentId() == 0).map(item -> {
            AttributeTreeVO vo = new AttributeTreeVO();
            //赋值给视图
            vo.setKeyId(item.getId());
            vo.setKeyName(item.getName());
            //查找二级分类赋值
            vo.setProductAttributeValueDtoList(productAttributeValueDtoList(item, list));
            return vo;
            //integers.contains(?) 判断是否有这个值  true/false
        }).collect(Collectors.toList());
    }

    /**
     * 根据1级查找二级属性
     *
     * @param vo   一级属性
     * @param list 属性所有信息
     * @return 二级属性
     */
    private List<ProductAttributeValueDtoListVO> productAttributeValueDtoList(ProductAttribute vo, List<ProductAttribute> list) {
        return list.stream()
                //删选出匹配一级分类id的对象
                .filter(level2 -> level2.getParentId().equals(vo.getId())).map(it -> {
                    //进行赋值给二级属性
                    return new ProductAttributeValueDtoListVO(it.getId(), it.getName());
                }).collect(Collectors.toList());
    }
}