package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.CartMess;
import org.apache.ibatis.annotations.Param;

/**
 * <p>项目文档： 购物车消息入库</p>
 * @author liming
 * @version 1.0.0
 * @createTime 2022年05月31日 20:36:00
 */
public interface CartMessMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CartMess record);

    CartMess selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CartMess record);

    Long selectIdBySkuidAndUseridAndUserid(@Param("skuId") Long skuId, @Param("userId") Long userId);
}