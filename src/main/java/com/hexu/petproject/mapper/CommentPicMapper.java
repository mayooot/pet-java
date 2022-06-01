package com.hexu.petproject.mapper;

import com.hexu.petproject.model.pojo.CommentPic;

import java.util.List;

public interface CommentPicMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CommentPic record);

    CommentPic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentPic record);
    List<CommentPic> list();
}