package com.hexu.petproject.model.pojo;

import java.io.Serializable;
import lombok.Data;

/**
    * 省市区字典表
    */
@Data
public class PcdDic implements Serializable {
    private Integer id;

    /**
    * 名称
    */
    private String name;

    /**
    * 上级编号：parentId=id -> 省/直辖市
    */
    private Integer parentId;

    private static final long serialVersionUID = 1L;
}