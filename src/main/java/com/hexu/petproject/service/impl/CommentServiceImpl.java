package com.hexu.petproject.service.impl;

import com.hexu.petproject.common.Page;
import com.hexu.petproject.mapper.CommentMapper;
import com.hexu.petproject.mapper.CommentPicMapper;
import com.hexu.petproject.mapper.SkuMapper;
import com.hexu.petproject.model.condition.CommentListCondition;
import com.hexu.petproject.model.condition.PostCommentCondition;
import com.hexu.petproject.model.pojo.Comment;
import com.hexu.petproject.model.pojo.CommentPic;
import com.hexu.petproject.model.pojo.User;
import com.hexu.petproject.model.vo.commentVO.CommentVO;
import com.hexu.petproject.service.CommentService;
import com.hexu.petproject.util.RedisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Classname CommentServiceImpl
 * @Description 评论
 * @Author 86176
 * @Date 2022-04-06 21:31
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private SkuMapper skuMapper;
    @Resource
    private CommentPicMapper commentPicMapper;

    @Override
    public Page<List<CommentVO>> list(CommentListCondition commentListCondition) {
        //查询出所有商品评价表
        List<CommentPic> commentPicList = commentPicMapper.list();
        //查询出商品评价表对应的信息
        List<CommentVO> commentVOList = commentMapper.list(commentListCondition)
                .stream().map(it -> {
                    //获取到图片集合  存储到setImgList里面去  这是个图片存储信息
                    List<String> collect = commentPicList.stream()
                            //过滤去评论图片不为空的commentid  不然会出现空指针
                            .filter(x -> !StringUtils.isEmpty(x.getCommentId()))
                            //封装成一个集合
                            .collect(Collectors.toList())
                            .stream()
                            //进行连表判断   过滤出评论表的主键id与评论图片表的commentid的信息
                            .filter(item -> item.getCommentId().equals(it.getCommentId())).
                            //只返回图片信息
                                    map(CommentPic::getPicUrl)
                            //封装成个集合返回
                            .collect(Collectors.toList());
                    it.setImgList(collect);
                    return it;
                }).collect(Collectors.toList())
                //把CommentVO中的图片存储信息进行修改  因为数据库存储的跟oss中的不对应
                // 要加上https://pet-object.oss-cn-beijing.aliyuncs.com/服务器前奏
                .stream()
                //预防空指针
                .filter(image -> image.getImgList().size() > 0).map(item -> {
                            for (int i = 0; i < item.getImgList().size(); i++) {
                                //set  给特定位置上的值进行更换
                                item.getImgList().set(i, "https://pet-object.oss-cn-beijing.aliyuncs.com/" + item.getImgList().get(i));
                            }
                            return item;
                        }
                ).collect(Collectors.toList());
        //把CommentVO中的图片存储信息进行修改  因为数据库存储的跟oss中的不对应  要加上https://pet-object.oss-cn-beijing.aliyuncs.com/服务器前奏
       /* commentVOList.forEach(item -> {
            //预防空指针
            if (item.getImgList() != null) {
                for (int i = 0; i < item.getImgList().size(); i++) {
                    //set  给特定位置上的值进行更换
                    item.getImgList().set(i, "https://pet-object.oss-cn-beijing.aliyuncs.com/" + item.getImgList().get(i));
                }
            }
        });*/

        //获取总页数
        Integer total = commentMapper.countByPrimaryKey(commentListCondition);
        Page<List<CommentVO>> page = new Page<>();
        //页数
        page.setPageNo(commentListCondition.getPageNo());
        //总数
        page.setTotal(total);
        //数据
        page.setList(commentVOList);
        return page;
    }

    /**
     * 发表评价接口
     *
     * @param postCommentCondition 前端参数实体
     */
    @Override
    public void postComment(PostCommentCondition postCommentCondition) {
        //先获取token中存储的用户信息
        User o = (User) redisTemplate.opsForValue().get(RedisUtils.send(postCommentCondition.getToken()));
        //获取spu的id
        Long byId = skuMapper.getById(postCommentCondition.getSpuId());
        //把评论增加到评论表
        Comment comment = new Comment();
        comment.setSpuId(byId);
        comment.setStar(postCommentCondition.getStar());
        comment.setContent(postCommentCondition.getContent());
        comment.setUserId(o != null ? o.getId() : null);
        commentMapper.insertSelective(comment);
          if (null != postCommentCondition.getImgIds()) {
           String[] split = postCommentCondition.getImgIds().split(",");
            //图片增加到商品评价图片表
            for (String s : split) {
                CommentPic commentPic = new CommentPic();
                commentPic.setCommentId(comment.getId());
                commentPic.setPicUrl(s);
                commentPicMapper.insertSelective(commentPic);
            }
            }
    }
}
