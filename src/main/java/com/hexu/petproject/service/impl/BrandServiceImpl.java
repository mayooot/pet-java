package com.hexu.petproject.service.impl;

import com.hexu.petproject.mapper.BrandMapper;
import com.hexu.petproject.model.pojo.Brand;
import com.hexu.petproject.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname BrandServiceImpl
 * @Description 品牌
 * @Author 86176
 * @Date 2022-04-04 14:53
 * @Version 1.0
 **/
@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;

    @Override
    public List<Brand> list() {
        return brandMapper.list();
    }
}
