package com.hexu.petproject.model.vo.categoryVO;

import com.hexu.petproject.common.SystemConstants;
import lombok.ToString;

import java.util.List;

/**
 * @Classname CategoryTreeVO
 * @Description  一级分类
 * @Author 86176
 * @Date 2022-04-02 11:24
 * @Version 1.0
 **/
@ToString
public class CategoryTreeVO {
    //一级分类ID
    private Long id;
    //分类名称
    private String name;
    //分类级别：0->1级；1->2级
    private Integer level;
    //| 显示状态：0->不显示；1->显示 |
    private Integer showStatus;
    //| 图标 |
    private String icon;
    //分类名称简称
    private String abbreviation;
    //商品二级分类列表
    private List<SecProductCategory> secProductCategoryList;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getIcon() {
        return this.icon;

    }

    public void setIcon(String icon) {
        if (!icon.contains(SystemConstants.OSS_PICTURE_PREFIX)) {
            this.icon = SystemConstants.OSS_PICTURE_PREFIX + icon;
        } else {
            this.icon = icon;
        }
    }

    public List<?> getSecProductCategoryList() {
        return secProductCategoryList;
    }

    public void setSecProductCategoryList(List<SecProductCategory> secProductCategoryList) {
        this.secProductCategoryList = secProductCategoryList;
    }
}
