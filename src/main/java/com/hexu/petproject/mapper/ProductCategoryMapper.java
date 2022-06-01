package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.ProductCategory;
import com.hexu.petproject.model.vo.categoryVO.CategoryTreeVO;
import com.hexu.petproject.model.vo.skuVO.CategoriesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductCategory record);
    /**
     * 查询一级分类
     * @return
     */
    List<ProductCategory> list();

    /**
     * SQL形式的分类显示 弃用
     * @return
     */
    List<CategoryTreeVO> list2();

    /**
     * 查询一级二级信息
     * @param cid1 一级
     * @param cid2 二级
     * @return
     */
    List<CategoriesVO> getListBySpuCid1AndCid2(@Param("cid1") Long cid1,@Param("cid2") Long cid2);
}