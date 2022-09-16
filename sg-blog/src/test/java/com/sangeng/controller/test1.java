package com.sangeng.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @ClassName ArticleControllerTest
 * @Description 日志功能以及发送邮件功能
 * @Date 2022/9/16 17:27
 */
@Slf4j
@SpringBootTest
class test1 {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;



    @Test

    public void test1() {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        log.info("asd");
        log.warn("warn");
        log.error("eoor");
        //谁要接收
        message.setTo("1960870058@qq.com");
        //邮件标题
        message.setSubject("test");
        //邮件内容
        message.setText("测试一下邮箱功能");
        try {
            javaMailSender.send(message);
            System.out.println("发送成功");
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("普通邮件方失败");
        }

    }

}