package com.hexu.petproject.model.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>项目文档： TODO</p>
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月28日 17:52:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * 用户ID

    */
    private Long userid;

    /**
    * 商品ID

    */
    private Long skuid;

    /**
    * 购买数量
    */
    private Integer num;

    private static final long serialVersionUID = 1L;

    public Cart(Long userid, Long skuid, Integer num) {
        this.userid = userid;
        this.skuid = skuid;
        this.num = num;
    }
}