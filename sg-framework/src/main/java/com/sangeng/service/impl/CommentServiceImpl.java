package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.entity.Comment;
import com.sangeng.entity.R.ResponseResult;
import com.sangeng.entity.User;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.CommentMapper;
import com.sangeng.service.CommentService;
import com.sangeng.service.UserService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.vo.CommentVo;
import com.sangeng.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-08-13 22:32:25
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(Long articalId, Integer pageNum, Integer pageSize) {
        //查询文章对应的根评论
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getArticleId,articalId);
        commentLambdaQueryWrapper.eq(Comment::getRootId,-1);
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page,commentLambdaQueryWrapper);
        //分页查询
        /**
         * return的时候一定要与前端沟通,看他希望你返回一个什么对象,你在vo里面包装
         * vo list集合
         * total 总的父评论条数
         */
        List<CommentVo> tocommentvolist = tocommentvolist(page.getRecords());
        /**
         * @author: 83799
         * @date: 2022/8/16 16:31
         * @description: 如果有子评论则还需要children属性
         */
        for (CommentVo commentVo : tocommentvolist) {
//            只有父评论才需要children,子评论没有子子评论
            List<CommentVo> getchildren = getchildren(commentVo.getId());
            commentVo.setChildren(getchildren);
        }
        return ResponseResult.okResult(new PageVo(tocommentvolist,page.getTotal()));
    }

    /**
     * @author: 83799
     * @date: 2022/8/16 16:39
     * @description: commentList ---> commentvolist
     * @Param:* @Param null:
     * @Return:* @return: null
     */
    private List<CommentVo> tocommentvolist(List<Comment> commentList){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            User byId = userService.getById(commentVo.getCreateBy());
//            这条评论是谁发的，这不管是父评论还是子评论都需要的
            commentVo.setUsername(byId.getNickName());
//            说明他是子评论
            if(commentVo.getToCommentUserId()!=-1){
//                评论发给谁 ，只有子评论才需要这个
                String nickName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(nickName);
            }
        }
        return commentVos;
    }
    /**
     * @author: 83799
     * @date: 2022/8/16 16:41
     * @description: 根据父评论id查询它的所有子评论
     * @Param:* @Param null:
     * @Return:* @return: null
     */
    public List<CommentVo> getchildren(Long id){
        LambdaQueryWrapper<Comment> qw = new LambdaQueryWrapper<>();
//        自己看表去,把 id拿出那,一一去和rootid比较,相等则是其子评论
        qw.eq(Comment::getRootId,id);
        qw.orderByAsc(Comment::getCreateTime);
        List<Comment> list = list(qw);
        List<CommentVo> tocommentvolist = tocommentvolist(list);
        return tocommentvolist;
    }
}
