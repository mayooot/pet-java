package com.hexu.petproject.mapper;

import com.hexu.petproject.model.condition.CommentListCondition;
import com.hexu.petproject.model.pojo.Comment;
import com.hexu.petproject.model.vo.commentVO.CommentVO;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    /**
     * 查询评价列表接口
     * @param commentListCondition 参数
     * @return
     */
    List<CommentVO> list(CommentListCondition commentListCondition);

    Integer countByPrimaryKey(CommentListCondition commentListCondition);
}