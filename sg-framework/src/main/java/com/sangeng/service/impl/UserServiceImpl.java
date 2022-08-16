package com.sangeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.entity.R.ResponseResult;
import com.sangeng.entity.User;
import com.sangeng.mapper.UserMapper;
import com.sangeng.service.UserService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.SecurityUtils;
import com.sangeng.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-08-11 13:48:47
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userinfo() {
//        工具包获取userid
        Long userId = SecurityUtils.getUserId();
        User byId = getById(userId);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(byId, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
}
