package com.sangeng.controller;


import com.sangeng.entity.R.ResponseResult;
import com.sangeng.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 友链(Link)表控制层
 *
 * @author makejava
 * @since 2022-08-10 20:02:09
 */
@RestController
@RequestMapping("link")
public class LinkController {
    /**
     * 无参，返回全部友联
     * @return
     */
    @Autowired
    private LinkService linkService;
    @GetMapping("/getAllLink")
    public ResponseResult getalllink(){
        return linkService.getalllink();
    }
   
}

