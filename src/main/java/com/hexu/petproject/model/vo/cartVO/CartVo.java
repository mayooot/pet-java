package com.hexu.petproject.model.vo.cartVO;

import com.hexu.petproject.common.SystemConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author YanCh
 * @description 购物车数据展示对象
 * @date 2022/4/9 13:45
 */
public class CartVo implements Serializable {

    /**
     * 商品 ID
     */
    private Long spuId;

    /**
     * 商品 sku ID
     */
    private Long skuId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品默认规格图片 URL
     */
    private String picUrl;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 购物车中某类商品的购买数量
     */
    private Integer num;

    /**
     * 每类商品的总价
     */
    private BigDecimal price;

    /**
     * 数量单位（袋）
     */
    private String unit;

    /**
     * 对应sku表中的商品库存stock
     */
    private transient Integer max;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 商品规格
     */
    private String productSkuSpecification;

    /**
     * 上架状态：0->下架；1->上架
     */
    private Integer publishStatus;


    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getProductSkuSpecification() {
        return productSkuSpecification;
    }

    public void setProductSkuSpecification(String productSkuSpecification) {
        this.productSkuSpecification = productSkuSpecification;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
        if (!picUrl.contains(SystemConstants.OSS_PICTURE_PREFIX)) {
            this.picUrl = SystemConstants.OSS_PICTURE_PREFIX + picUrl;
        } else {
            this.picUrl = picUrl;
        }
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
}
