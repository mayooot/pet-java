package com.hexu.petproject.service.impl;

import com.hexu.petproject.mapper.PcdDicMapper;
import com.hexu.petproject.mapper.ReceiverAddressMapper;
import com.hexu.petproject.model.condition.AddressCondition;
import com.hexu.petproject.model.pojo.PcdDic;
import com.hexu.petproject.model.pojo.ReceiverAddress;
import com.hexu.petproject.model.pojo.User;
import com.hexu.petproject.model.vo.pcdDicVO.DicVO;
import com.hexu.petproject.model.vo.pcdDicVO.PcdList;
import com.hexu.petproject.model.vo.receiverAddressVO.ListVO;
import com.hexu.petproject.service.UmsRceiverAddressService;
import com.hexu.petproject.util.RedisUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname ReceiverAddressServiceImpl
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-09 15:59
 * @Version 1.0
 **/
@Service
public class UmsRceiverAddressServiceImpl implements UmsRceiverAddressService {

    @Resource
    private PcdDicMapper pcdDicMapper;

    @Resource
    private ReceiverAddressMapper receiverAddressMapper;

    @Resource
    private RedisTemplate< String,Object> redisTemplate;

    /**
     * 三级省市区
     *
     * @return
     */
    @Cacheable(value = "pet:PcdDic", key = "#root.methodName")
    @Override
    public List<DicVO> dicList() {
        List<PcdDic> list = pcdDicMapper.list();
        return list.stream().filter(id -> id.getParentId() == 0).map(item -> {
            DicVO vo = new DicVO();
            //赋值给视图
            BeanUtils.copyProperties(item, vo);
            //查找二级分类赋值
            vo.setPcdList(secProductCategoryList(item, list));
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 查询用户收货地址
     *
     * @param id 用户id
     * @return
     */
    @Override
    public List<ListVO> list(Integer userId, String id) {
        List<ReceiverAddress> list =  receiverAddressMapper.getByUserId(userId);
        List<ListVO> collect = list.stream().map(item -> {
            ListVO listVo = new ListVO();
            BeanUtils.copyProperties(item, listVo);
            listVo.setName(item.getUserName());
            //3级
            if (item.getCountyId() != null) {
                PcdDic pcdDic1 = pcdDicMapper.selectByPrimaryKey(item.getCountyId());
                //2级
                PcdDic pcdDic2 = pcdDicMapper.selectByPrimaryKey(pcdDic1.getParentId());
                //1级
                PcdDic pcdDic3 = pcdDicMapper.selectByPrimaryKey(pcdDic2.getParentId());
                //获取省
                listVo.setProvince(pcdDic1.getName());
                //获取市
                listVo.setCity(pcdDic2.getName());
                //获取区
                listVo.setCounty(pcdDic3.getName());
            }

            return listVo;
        }).collect(Collectors.toList());
        return collect;
    }
    @Override
    public Boolean remove(Long addressId, String token) {
        int i = receiverAddressMapper.deleteByPrimaryKey(addressId);
        return i > 0;
    }

    @Override
    public void saveOrUpdate(AddressCondition addressCondition) {
        //获取用户信息
        String user = RedisUtils.send(addressCondition.getToken());
        User o = (User) redisTemplate.opsForValue().get(user);
        ReceiverAddress receiverAddress = new ReceiverAddress();
        receiverAddress.setCountyId(addressCondition.getCountyId());
        receiverAddress.setUserName(addressCondition.getUserName());
        receiverAddress.setPhoneNumber(addressCondition.getPhoneNumber());
        receiverAddress.setDefaultStatus(addressCondition.getDefaultStatus());
        receiverAddress.setDetailAddress(addressCondition.getDetailAddress());
        receiverAddress.setUserId(o != null ? o.getId() : null);
        if (null == addressCondition.getId()) {
            receiverAddressMapper.insertSelective(receiverAddress);
        } else {
            receiverAddress.setId(addressCondition.getId());
            receiverAddressMapper.updateByPrimaryKeySelective(receiverAddress);
        }
    }

    /**
     * 根据1级查找二级分类
     *
     * @param vo   一级分类
     * @param list 分类所有信息
     * @return 二级分类
     */
    private List<PcdList> secProductCategoryList(PcdDic vo, List<PcdDic> list) {
        return list.stream()
                //删选出匹配一级分类id的二级对象
                .filter(level2 -> level2.getParentId().equals(vo.getId())).map(it -> {
                    //删选出匹配一级分类id的三级对象
                    List<PcdList> pcdLists = secProductCategoryList(it, list);
                    //查询出3级分类
                    return new PcdList(it.getId(), it.getName(), pcdLists);
                }).collect(Collectors.toList());
    }
}
