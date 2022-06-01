package com.hexu.petproject.model.condition;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Classname CommentCondition
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-06 20:47
 * @Version 1.0
 **/
@NoArgsConstructor
public class CommentListCondition {
    //商品ID
    private Long spuId;
    //方式：0:无图评价；1: 有图评价；null: 全部评价
    private Integer isPic;
    //页码（从1开始），默认1
    private Integer pageNo = 0;
    //页长，默认20
    private Integer pageSize = 20;
    //每页起始下标
    private Integer startIndex = 0;

    public CommentListCondition(Long spuId, Integer isPic, Integer pageNo) {
        this.spuId = spuId;
        this.isPic = isPic;
        this.pageNo = pageNo;
        this.startIndex = (this.getPageNo() - 1) * this.getPageSize();
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Integer getIsPic() {
        return isPic;
    }

    public void setIsPic(Integer isPic) {
        this.isPic = isPic;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;

    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;

    }
}