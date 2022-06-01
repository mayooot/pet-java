package com.hexu.petproject.model.vo.attributeVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname ProductAttributeValueDtoListVO
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-04 13:34
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeValueDtoListVO {
    //商品属性value值ID
    private Long valueId;
    //商品属性value值列表
    private String valueName;
}
