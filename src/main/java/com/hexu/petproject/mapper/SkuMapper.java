package com.hexu.petproject.mapper;

import com.hexu.petproject.model.condition.ProductSearchCondition;
import com.hexu.petproject.model.pojo.Sku;
import com.hexu.petproject.model.vo.cartVO.CartVo;
import com.hexu.petproject.model.vo.skuVO.ProductVO;
import com.hexu.petproject.model.vo.skuVO.SkuList;
import com.hexu.petproject.model.vo.skuVO.SpuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Sku record);

    Sku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sku record);

    /**
     * 分页条件查询
     * @param productSearchCondition 条件
     * @return
     */
    List<ProductVO> getProductSearchCondition(ProductSearchCondition productSearchCondition);

    /**
     * 获取总数量
     * @param productSearchCondition 条件
     * @return
     */
    Integer countByPrimaryKey(ProductSearchCondition productSearchCondition);

    /**
     * 根据sku编号查询商品spu信息
     * @param id sku编号
     * @return
     */
    SpuVO getSkuBySpuOne(Long id);


    /**
     * 根据spu编号查询出所有sku的id  再去关联pms_product_detail_pic 的spuid 查询出图片信息
     * @param spuId spu编号
     * @return
     */
    List<SkuList> skuList(Long spuId);

    /**
     * 根据id查询出spuid
     * @param id skuid
     * @return
     */
    Long getById(Long id);

    /**
     * 查询购物车商品单个数据
     * @param skuId 商品id
     * @return
     */
    CartVo selectCartVOBySkuId(Long skuId);


    /**
     * 扣减库存
     * @param skuId 商品ID
     * @param skuNum 商品购买数量
     * @return
     */
    int deductedInventory(@Param("skuId") Long skuId, @Param("skuNum") Integer skuNum);
}