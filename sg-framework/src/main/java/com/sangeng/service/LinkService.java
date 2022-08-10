package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.entity.Link;
import com.sangeng.entity.R.ResponseResult;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-08-10 20:02:01
 */
public interface LinkService extends IService<Link> {

    ResponseResult getalllink();
}
