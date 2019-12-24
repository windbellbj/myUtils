package com.htdk.utils.quartz.common;

/**
 * @desc   Quartz设置项目全局的定时任务
 * @auther xin.long.li
 * @create 2019/12/04
 */
import com.htdk.utils.ftp.InsertTable;
import com.htdk.utils.ftp.SqlProperties;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${sqlserver.driver}")
    public String driver;
    @Value("${sqlserver.url}")
    public String url;
    @Value("${sqlserver.user}")
    public String user;
    @Value("${sqlserver.passWord}")
    public String passWord;

    @Value("${sqlserver.urlTMS}")
    public String urlTMS;

    @Scheduled(cron = "${quartz.one}") // 每分钟执行一次
    public void work(){
        System.out.println("执行调度任务： xmlToCvs-------------------start:");
        SqlProperties properties = new SqlProperties(driver, url, user, passWord,urlTMS);
        InsertTable.start(properties);

        System.out.println("执行调度任务： xmlToCvs-------------------end:");

    }

}