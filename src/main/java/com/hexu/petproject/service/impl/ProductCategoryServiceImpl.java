package com.hexu.petproject.service.impl;

import com.hexu.petproject.mapper.ProductCategoryMapper;
import com.hexu.petproject.model.pojo.ProductCategory;
import com.hexu.petproject.model.vo.categoryVO.CategoryTreeVO;
import com.hexu.petproject.model.vo.categoryVO.SecProductCategory;
import com.hexu.petproject.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname ProductCategoryServiceImpl
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-02 11:48
 * @Version 1.0
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 获取树
     * @return 树
     */
    @Cacheable(value = "pet:ProductCategory",key = "#root.methodName")
    @Override
    public List<CategoryTreeVO> listAll() {
        List<ProductCategory> list = productCategoryMapper.list();
        return list.stream().filter(id -> id.getParentId() == 0).map(item -> {
          CategoryTreeVO vo = new CategoryTreeVO();
          //赋值给视图
          BeanUtils.copyProperties(item, vo);
          //查找二级分类赋值
            vo.setSecProductCategoryList(secProductCategoryList(item, list));
          return vo;
      }).collect(Collectors.toList());

    }


    /**
     * 根据1级查找二级分类
     * @param vo 一级分类
     * @param list 分类所有信息
     * @return 二级分类
     */
    private List<SecProductCategory> secProductCategoryList(ProductCategory vo,List<ProductCategory> list){
        return list.stream()
                //删选出匹配一级分类id的对象
                .filter(level2 -> level2.getParentId().equals(vo.getId())).map(it -> {
                    //进行赋值给二级分类
                    return new SecProductCategory(it.getId(),it.getName(),it.getLevel(),it.getShowStatus(),it.getIcon());
        }).collect(Collectors.toList());
    }

}
