package com.sangeng.controller;

import com.sangeng.constants.SystemConstants;
import com.sangeng.entity.Comment;
import com.sangeng.entity.R.ResponseResult;
import com.sangeng.service.CommentService;
import io.swagger.models.RefResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CommentController
 * @Description TODO
 * @Date 2022/8/15 0:34
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(articleId,pageNum,pageSize);
    }
// 前端做了处理,友联和评论通用这一个接口
    @PostMapping
    public ResponseResult addcomment(@RequestBody Comment comment){
        return commentService.addcomment(comment);
    }

    @GetMapping("/linkCommentList")
    public ResponseResult linkconmment(Integer pageNum,Integer pageSize){
        return commentService.showlinkcomment(pageNum,pageSize);
    }


}
