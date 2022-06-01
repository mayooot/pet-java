package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.Cart;
import org.apache.ibatis.annotations.Param;

/**
 * <p>项目文档： TODO</p>
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月28日 17:52:00
 */
public interface CartMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cart record);

    int deleteByUseridAndSkuid(@Param("userId") Long userId, @Param("skuId") Long skuId);
}