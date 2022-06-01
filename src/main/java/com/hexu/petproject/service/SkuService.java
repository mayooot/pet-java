package com.hexu.petproject.service;

import com.hexu.petproject.common.Page;
import com.hexu.petproject.model.condition.ProductSearchCondition;
import com.hexu.petproject.model.pojo.OrderItem;
import com.hexu.petproject.model.vo.cartVO.CartVo;
import com.hexu.petproject.model.vo.skuVO.ProductVO;
import com.hexu.petproject.model.vo.skuVO.SkuDetail;

import java.util.List;

/**
 * @Classname ProductService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-01 14:56
 * @Version 1.0
 **/
public interface SkuService {
    /**
     * 分页条件查询
     * @param productSearchCondition 条件
     * @return
     */
    Page<List<ProductVO>> page(ProductSearchCondition productSearchCondition);

    /**
     * 查询商品详细
     * @param id sku编号
     * @return
     */
    SkuDetail skuDetailOne(Long id);

    /**
     * 查询商品对应的信息
     * @param skuId 商品编号
     */
    CartVo getCartByCartVo(Long skuId);

    /**
     * 修改对应的库存数量
     * @param skuId 编号
     * @param skuNum 数量
     */
    void delTheInventory(Long skuId, Integer skuNum);

    /**
     * 修改库存
     * @param orderItem
     */
    void rollBackTheInventory(OrderItem orderItem);

    /**
     * 扣减库存
     * @param skuId 商品ID
     * @param skuNum 商品购买数量
     * @return
     */
    int deductedInventory(Long skuId, Integer skuNum);
}
