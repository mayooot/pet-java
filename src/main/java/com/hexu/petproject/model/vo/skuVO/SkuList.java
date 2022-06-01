package com.hexu.petproject.model.vo.skuVO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Classname SkuList
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-05 14:54
 * @Version 1.0
 **/
@Data
public class SkuList {
    //商品skuID
    private Long id;
    //商品spuID
    private Long spuId;
    //商品名称
    private String title;
    //商品价格
    private Double price;
    //商品单位
    private String unit;
    //商品库存
    private Integer stock;
    //商品sku规格(json格式)
    private String productSkuSpecification;
    //商品sku图片列表
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SkuPicList> skuPicList;


}
