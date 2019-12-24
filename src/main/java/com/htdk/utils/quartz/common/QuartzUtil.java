package com.htdk.utils.quartz.common;

/**
 * @desc   Quartz设置项目全局的定时任务
 * @auther lixinlong
 * @create 2018/5/18
 */
import java.util.Date;

import com.htdk.utils.controller.PiUtilsController;
import com.htdk.utils.controller.SendMailController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/***
 *
 * Quartz设置项目全局的定时任务
 *
 * @Component注解的意义        泛指组件，当组件不好归类的时候，可以使用这个注解进行标注。公共的方法可以用上这个注解
 *
 *
 * @author lixinlong
 *
 */
@Component
public class QuartzUtil {
    @Autowired
    public PiUtilsController controller;

    @Autowired
    public SendMailController sendMailController;

    @Scheduled(cron = "30 * * * * ?") // 每分钟执行一次
    public void work() throws Exception {
        System.out.println("执行调度任务： xmlToCvs-------------------start:");

        controller.xmlToCvs();

        System.out.println("执行调度任务： xmlToCvs-------------------end:");

    }

    @Scheduled(cron = "59 * * * * ?") // 每分钟执行一次
    public void work2() throws Exception {
        System.out.println("执行调度任务： xmlToCvs2-------------------start:");

        sendMailController.send();

        System.out.println("执行调度任务： xmlToCvs2-------------------end:");

    }

}