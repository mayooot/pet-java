package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.MiaoshaSku;
import com.hexu.petproject.model.pojo.Sku;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年06月07日 23:50:00
 */
public interface MiaoshaSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(MiaoshaSku record);

    MiaoshaSku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiaoshaSku record);

    int updateStockAndVersionById(MiaoshaSku sku);
}