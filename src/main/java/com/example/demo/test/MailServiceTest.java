package com.example.demo.test;

import com.example.demo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by HZL
 * @date 2019/11/29 15:16
 * @description 测试文本邮件
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleTextMailTest(){
        String to="2352823890@qq.com";
        String subject="SpringBoot测试邮件";
        String content = "<p>第一封 Springboot 简单文本邮件</p>";
        mailService.sendSimpleTextMail(to,subject,content);
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String to="2352823890@qq.com";
        String subject="SpringBoot测试邮件";
        String content = "<h2>Hi~</h2><p>第一封 Springboot 简单文本邮件</p>";
        mailService.sendHtmlMail(to,subject,content);
    }

    @Test
    public void sendAttachmentTest() throws MessagingException {
        String to = "2352823890@qq.com";
        String subject = "Springboot 发送 HTML 附件邮件";
        String content = "<h2>Hi~</h2><p>第一封 Springboot HTML 附件邮件</p>";
        String filePath = "pom.xml";
        mailService.sendAttachmentMail(to, subject, content, filePath, filePath);
    }

    @Test
    public void sendImgTest() throws MessagingException {
        String to = "2352823890@qq.com";
        String subject = "Springboot 发送 HTML 图片邮件";
        String content =
                "<h2>Hi~</h2><p>第一封 Springboot HTML 图片邮件</p><br/><img src=\"cid:img01\" /><img src=\"cid:img02\" />";
        String imgPath = "1.jpg";
        Map<String, String> imgMap = new HashMap<>();
        imgMap.put("img01", imgPath);
        imgMap.put("img02", imgPath);
        mailService.sendImgMail(to, subject, content, imgMap);
    }

    @Test
    public void sendTemplateMailTest() throws MessagingException {
        String to = "2352823890@qq.com";
        String subject = "Springboot 发送 模版邮件";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("username", "Darcy");
        mailService.sendTemplateMail(to, subject, paramMap, "RegisterSuccess");
    }
}
