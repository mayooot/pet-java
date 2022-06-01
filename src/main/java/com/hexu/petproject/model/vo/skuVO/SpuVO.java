package com.hexu.petproject.model.vo.skuVO;

import lombok.Data;

/**
 * @Classname SpuVO
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-05 14:40
 * @Version 1.0
 **/
@Data
public class SpuVO {
    //商品spuID
    private Long id;
    //商品spuID
    private Long spuId;
    //1
    private Long cid1;
    //2
    private Long cid2;
    //商品名称
    private String name;
    //商品副标题
    private String subTitle;
    //商品总销量
    private Integer sale;
    //商品评价数量
    private Integer commentAmount;
    //评价总评分
    private Integer commentTotalScore;
    //商品规格(json格式，用于商品详情页展示商品所有规格
    private String productSpecification;
    //商品详情网页内容
    private String detailHtml;

}
