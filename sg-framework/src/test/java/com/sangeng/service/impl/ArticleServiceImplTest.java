package com.sangeng.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @ClassName ArticleServiceImplTest
 * @Description TODO
 * @Date 2022/8/13 17:04
 */
class ArticleServiceImplTest {

    @Test
    void hotArticlelist() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);

    }


}