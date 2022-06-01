package com.hexu.petproject.model.vo.skuVO;

import lombok.Data;

import java.util.List;

/**
 * @Classname SkuDetail
 * @Description 查看商品详情
 * @Author 86176
 * @Date 2022-04-05 14:59
 * @Version 1.0
 **/
@Data
public class SkuDetail {
    //商品spu信息
    private SpuVO spu;
    //商品分类数组
    private List<CategoriesVO> categories;
    //商品sku列表
    private List<SkuList> skuList;
}
