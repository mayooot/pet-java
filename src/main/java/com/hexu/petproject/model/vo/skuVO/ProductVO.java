package com.hexu.petproject.model.vo.skuVO;

import com.hexu.petproject.common.SystemConstants;

import java.io.Serializable;

/**
 * @Classname productVO
 * @Description  商品搜索返回的结果
 * @Author 86176
 * @Date 2022-03-31 18:21
 * @Version 1.0
 **/
public class ProductVO implements Serializable {
    //主键ID
    private Integer id;
    //商品名称
    private String name;
    //商品销量
    private Integer sale;
    //商品默认图片URL
    private String  defaultPicUrl;
    //商品默认价格
    private Double defaultPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getDefaultPicUrl() {
        return this.defaultPicUrl;
    }

    public void setDefaultPicUrl(String defaultPicUrl) {

        if (!defaultPicUrl.contains(SystemConstants.OSS_PICTURE_PREFIX)) {
            this.defaultPicUrl = SystemConstants.OSS_PICTURE_PREFIX + defaultPicUrl;
        } else {
            this.defaultPicUrl = defaultPicUrl;
        }
    }

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
