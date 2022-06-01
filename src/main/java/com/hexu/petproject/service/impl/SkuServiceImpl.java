package com.hexu.petproject.service.impl;

import com.hexu.petproject.common.Page;
import com.hexu.petproject.mapper.ProductCategoryMapper;
import com.hexu.petproject.mapper.SkuMapper;
import com.hexu.petproject.model.condition.ProductSearchCondition;
import com.hexu.petproject.model.pojo.OrderItem;
import com.hexu.petproject.model.pojo.Sku;
import com.hexu.petproject.model.vo.cartVO.CartVo;
import com.hexu.petproject.model.vo.skuVO.*;
import com.hexu.petproject.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname ProductServiceImpl
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-01 14:57
 * @Version 1.0
 **/
@Service
@Slf4j
public class SkuServiceImpl implements SkuService {
    @Resource
    private SkuMapper skuMapper;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private ProductCategoryMapper productCategoryMapper;
    @Override
    public Page<List<ProductVO>> page(ProductSearchCondition productSearchCondition) {
        List<ProductVO> spuList = skuMapper.getProductSearchCondition(productSearchCondition);
        //获取总页数
        Integer total = skuMapper.countByPrimaryKey(productSearchCondition);
        Page<List<ProductVO>> page = new Page<>();
        //页数
        page.setPageNo(productSearchCondition.getPageNo());
        //总数
        page.setTotal(total);
        //数据
        page.setList(spuList);
        return page;
    }

    /**
     * 查询商品详细
     * @param id sku编号
     * @return 商品详细
     */
    @Override
    public SkuDetail skuDetailOne(Long id) {
        long l = System.currentTimeMillis();
        //商品详细组合
        SkuDetail skuDetail = new SkuDetail();
        CompletableFuture<SpuVO> supplyAsync = CompletableFuture.supplyAsync(() -> {
            //商品spu信息
            SpuVO spuVO = skuMapper.getSkuBySpuOne(id);
            skuDetail.setSpu(spuVO);
            return spuVO;
        },threadPoolExecutor);
        CompletableFuture<Void> voidCompletableFuture1 = supplyAsync.thenAcceptAsync(spuVO -> {
            ////商品分类数组
            List<CategoriesVO> categoriesVOList = productCategoryMapper.getListBySpuCid1AndCid2(spuVO.getCid1(), spuVO.getCid2());
            skuDetail.setCategories(categoriesVOList);
        }, threadPoolExecutor);
        CompletableFuture<Void> voidCompletableFuture = supplyAsync.thenAcceptAsync(spuVO -> {
            //商品sku列表
            List<SkuList> skuList = skuMapper.skuList(spuVO.getSpuId());
            skuDetail.setSkuList(skuList);
        }, threadPoolExecutor);
        CompletableFuture.allOf(supplyAsync,voidCompletableFuture1,voidCompletableFuture).join();
        System.out.println(System.currentTimeMillis() - l);
        return skuDetail;
    }

    /**
     * 查询商品对应的信息
     *
     * @param skuId 商品编号
     */
    @Override
    public CartVo getCartByCartVo(Long skuId) {
        return skuMapper.selectCartVOBySkuId(skuId);
    }

    /**
     * 修改对应的库存数量
     *
     * @param skuId  编号
     * @param skuNum 数量
     */
    @Override
    public void delTheInventory(Long skuId, Integer skuNum) {
        //先查询商品对应的信息
        Sku sku = skuMapper.selectByPrimaryKey(skuId);
        //修改库存
        sku.setStock(sku.getStock() - skuNum);
        sku.setSale(sku.getSale() + skuNum);
        skuMapper.updateByPrimaryKeySelective(sku);
    }

    @Override
    public void rollBackTheInventory(OrderItem orderItem) {

        log.info("数据为{}", orderItem);
        Sku sku = skuMapper.selectByPrimaryKey(orderItem.getSkuId());

        // 回滚库存
        sku.setStock(sku.getStock() + orderItem.getProductQuantity());
        // 回滚销量
        sku.setSale(sku.getSale() - orderItem.getProductQuantity());

        // 更新
        skuMapper.updateByPrimaryKeySelective(sku);
    }

    @Override
    public int deductedInventory(Long skuId, Integer skuNum) {
        return skuMapper.deductedInventory(skuId, skuNum);
    }
}


