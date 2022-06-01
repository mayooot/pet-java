package com.hexu.petproject.model.vo.skuVO;

import com.hexu.petproject.common.SystemConstants;
import lombok.Data;

/**
 * @Classname SkuPicList
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-05 14:57
 * @Version 1.0
 **/
public class SkuPicList {
    //主键ID
    private Long id;
    //商品skuID
    private Long skuId;
    //商品sku图片URL
    private String picUrl;
    //默认展示：0->否；1->是
    private Integer isDefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
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

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
