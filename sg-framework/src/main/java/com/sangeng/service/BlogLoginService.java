package com.sangeng.service;

import com.sangeng.entity.R.ResponseResult;
import com.sangeng.entity.User;

/**
 * @ClassName BlogLoginService
 * @Description TODO
 * @Date 2022/8/13 13:14
 */
public interface BlogLoginService {
    ResponseResult login(User user);
}
