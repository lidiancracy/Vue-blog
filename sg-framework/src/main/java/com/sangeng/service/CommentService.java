package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.entity.Comment;
import com.sangeng.entity.R.ResponseResult;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-08-13 22:32:25
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articalId, Integer pageNum, Integer pageSize);

}
