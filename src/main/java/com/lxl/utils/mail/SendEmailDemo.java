package com.lxl.utils.mail;

import org.junit.Test;

import java.util.ArrayList;

/**
 * <dl>
 * <dt>SendEmailDemo</dt>
 * <dd>@auther lixinlong</dd>
 * <dd>@create 2018/8/3</dd>
 * <dd></dd>
 * </dl>
 */
public class SendEmailDemo {

    @Test
    public void test1(){

        String host = "smtp.qq.com";// use your smtp server host

        final String username = "761503057@qq.com"; // use your username
        final String password = "tswpviskdsagbdfj"; // use your password

        String from = "761503057@qq.com"; // use your sender email address

        String[] to = {"xinlong.li@vilang.com"};// use your reciever email address
        try {
            EmailHelper emailHelper = new EmailHelper(host, username, password, from);
            emailHelper.setTo(to);
            emailHelper.setSubject("subject ttt test");
            emailHelper.setHtmlContent("<h1> This is html </h1>");
            ArrayList<String> objects = new ArrayList<>();
            objects.add("/Users/lixinlong/Desktop/lkk/抽奖系统接口20180606.docx");
            emailHelper.setAttachedFileName(objects);

            emailHelper.send();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
