package com.hexu.petproject.model.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname PostCommentCondition
 * @Description 发表评价接口
 * @Author 86176
 * @Date 2022-04-08 12:08
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentCondition {
    private Long spuId;
    private String content;
    private Integer star;
    private String imgIds;
    private String token;
}
