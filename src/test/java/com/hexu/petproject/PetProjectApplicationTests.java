package com.hexu.petproject;


import com.hexu.petproject.mapper.PcdDicMapper;
import com.hexu.petproject.mapper.ProductCategoryMapper;
import com.hexu.petproject.model.pojo.PcdDic;
import com.hexu.petproject.model.pojo.ProductCategory;
import com.hexu.petproject.model.vo.categoryVO.CategoryTreeVO;
import com.hexu.petproject.model.vo.pcdDicVO.DicVO;
import com.hexu.petproject.service.CommentService;
import com.hexu.petproject.service.ProductCategoryService;
import com.hexu.petproject.service.impl.UmsRceiverAddressServiceImpl;

import com.hexu.petproject.util.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootTest
@Slf4j
class PetProjectApplicationTests {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private CommentService commentService;
    @Resource
    private ProductCategoryMapper productAttributeValueDtoList;


    @Test
    public void x() throws Exception {
        List<ProductCategory> list = productAttributeValueDtoList.list();
        Map<Long, List<ProductCategory>> collect = list.stream().collect(Collectors.groupingBy(ProductCategory::getParentId));
        collect.get(2L).forEach(System.out::println);

    }

    @Resource
    private UmsRceiverAddressServiceImpl pcdDicService;
    
    @Test
    public void x2() {
        List<Integer> collect = stringRedisTemplate.opsForSet().intersect("1", "2").stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(collect);

    }
    @Test
    public void x3() {
        log.info("1");
        log.error("2");
       stringRedisTemplate.opsForZSet().range("1",0,2)
               .stream().map(Integer ::valueOf).collect(Collectors.toList()).forEach(System.out::println);

    }
}