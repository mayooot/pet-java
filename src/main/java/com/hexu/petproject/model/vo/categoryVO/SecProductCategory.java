package com.hexu.petproject.model.vo.categoryVO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Classname SecProductCategory
 * @Description  二级分类
 * @Author 86176
 * @Date 2022-04-02 11:27
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
public class SecProductCategory {
    //二级分类ID
    private Long secId;
    //二级分类名称
    private String secName;
    //分类级别：0->1级；1->2级
    private Integer secLevel;
    //显示状态
    private Integer secShowStatus;
    //图标
    private String secIcon;

    public Long getSecId() {
        return secId;
    }

    public void setSecId(Long secId) {
        this.secId = secId;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public Integer getSecLevel() {
        return secLevel;
    }

    public void setSecLevel(Integer secLevel) {
        this.secLevel = secLevel;
    }

    public Integer getSecShowStatus() {
        return secShowStatus;
    }

    public void setSecShowStatus(Integer secShowStatus) {
        this.secShowStatus = secShowStatus;
    }

    public Object getSecIcon() {
        return secIcon;
    }

    public void setSecIcon(String secIcon) {
        this.secIcon = secIcon;
    }
}
