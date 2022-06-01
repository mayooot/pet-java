package com.hexu.petproject.model.vo.attributeVO;

import java.util.List;

/**
 * @Classname CategoryTreeVO
 * @Description  一级分类
 * @Author 86176
 * @Date 2022-04-02 11:24
 * @Version 1.0
 **/

public class AttributeTreeVO {
    //商品属性key值ID
    private Long keyId;
    //商品属性key值名称
    private String keyName;
    //商品属性value值列表
    private List<ProductAttributeValueDtoListVO> productAttributeValueDtoList;

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public List<?> getProductAttributeValueDtoList() {
        return productAttributeValueDtoList;
    }

    public void setProductAttributeValueDtoList(List<ProductAttributeValueDtoListVO> productAttributeValueDtoList) {
        this.productAttributeValueDtoList = productAttributeValueDtoList;
    }
}
