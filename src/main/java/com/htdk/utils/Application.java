package com.htdk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
/***
 * 	Github地址：https://github.com/windbellbj/myUtils.git
 * 	默认用户名：user
 * 	默认密码：启动后看控制台
 */
@EnableScheduling
@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

	@Autowired
	private Environment env;

	private static final Logger log = LoggerFactory.getLogger(Application.class);


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
