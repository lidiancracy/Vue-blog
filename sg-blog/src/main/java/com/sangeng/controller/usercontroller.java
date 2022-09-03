package com.sangeng.controller;

import com.sangeng.annotation.SystemLog;
import com.sangeng.entity.R.ResponseResult;
import com.sangeng.entity.User;
import com.sangeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName usercontroller
 * @Description TODO
 * @Date 2022/8/16 22:23
 */
@RestController
@RequestMapping("user")
public class usercontroller {
    @Autowired
    private UserService userService;
    @SystemLog(businessName = "查看用户信息")
    @GetMapping("/userInfo")
    public ResponseResult userinfo(){
        return userService.userinfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateuserinfo(@RequestBody User user){
        return  userService.updateuserinfo(user);
    }

    @PostMapping("/register")
    @SystemLog(businessName = "注册用户")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
