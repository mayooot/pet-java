package com.hexu.petproject.common;


import java.io.Serializable;

/**
 * @Classname Page
 * @Description  分页
 * @Author 86176
 * @Date 2022-03-31 18:29
 * @Version 1.0
 **/
public class Page<T> implements Serializable {

    //页数
    private Integer pageNo = 1;
    //条数
    private Integer pageSize = 20;
    //总数
    private Integer total;
    private Integer pages;
    private T list;


    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages = this.total % this.pageSize == 0 ?
                this.total : this.total / this.pageSize + 1;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
