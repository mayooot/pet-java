package com.hexu.petproject.controller;

import com.hexu.petproject.common.Page;
import com.hexu.petproject.interceptor.Authentication;
import com.hexu.petproject.model.condition.CommentListCondition;
import com.hexu.petproject.model.condition.PostCommentCondition;
import com.hexu.petproject.model.vo.commentVO.CommentVO;
import com.hexu.petproject.service.CommentService;
import com.hexu.petproject.util.ResultVO;
import com.hexu.petproject.util.SmsUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname CommentController
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-06 20:44
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/api/pms/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Authentication
    @GetMapping("/commentList")
    public ResultVO commentList(Long spuId, Integer isPic, Integer pageNo) {
        //字段封装成一个对象
        CommentListCondition commentListCondition = new CommentListCondition(spuId, isPic, pageNo);
        Page<List<CommentVO>> list = commentService.list(commentListCondition);
        return ResultVO.ok(list);
    }

    @PostMapping("/postComment")
    public ResultVO postComment(Long spuId, String content, Integer star, String imgIds, HttpServletRequest request) {
        String token = SmsUtils.token(request);
        PostCommentCondition postCommentCondition = new PostCommentCondition(spuId, content, star, imgIds, token);
        commentService.postComment(postCommentCondition);
        return ResultVO.ok();
    }
}
