package com.hexu.petproject.service;

import com.hexu.petproject.common.Page;
import com.hexu.petproject.model.condition.CommentListCondition;
import com.hexu.petproject.model.condition.PostCommentCondition;
import com.hexu.petproject.model.vo.commentVO.CommentVO;

import java.util.List;

/**
 * @Classname CommentService
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-06 21:31
 * @Version 1.0
 **/
public interface CommentService {
    /**
     * 查询所有评论  并查询出所有评论图片
     * @param commentListCondition 前端条件实体
     * @return
     */
    Page<List<CommentVO>> list(CommentListCondition commentListCondition);

    /**
     * 发表评价接口
     * @param postCommentCondition 前端参数实体
     */
    void postComment(PostCommentCondition postCommentCondition);
}
