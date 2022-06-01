package com.hexu.petproject.model.condition;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname ProductSearchCondition
 * @Description  商品搜索条件
 * @Author 86176
 * @Date 2022-03-31 18:08
 * @Version 1.0
 **/
public class ProductSearchCondition implements Serializable {
    //品牌ID
    private Integer brandId;
    //二级分类ID
    private Integer productCategoryId;
    //搜索条件
    private String keyword;
    //商品属性ID数组
    private List<Integer> productAttributeIds;
    //排序：1->默认；2->销量；3->评论
    private Integer sort;
    //页码（从1开始），默认1
    private Integer pageNo = 1;
    //页长，默认20
    private Integer pageSize = 20;
    //每页起始下标
    private Integer startIndex = 0;



    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;

    }


    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getProductAttributeIds() {
        return productAttributeIds;
    }

    public void setProductAttributeIds(List<Integer> productAttributeIds) {
        this.productAttributeIds = productAttributeIds;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        this.startIndex = (this.getPageNo() - 1) * this.getPageSize();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
