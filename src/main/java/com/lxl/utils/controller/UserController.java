package com.lxl.utils.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <dl>
 * <dt>UserController</dt>
 * <dd>@auther lixinlong</dd>
 * <dd>@create 2018/8/14</dd>
 * <dd></dd>
 * </dl>
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public RedisTemplate redisTemplate;

    @GetMapping
    public String getUsers() {
        Long aLong = redisTemplate.opsForList().rightPush("utils", "one");
        System.out.println(aLong);
        return "Hello Spring Security";
    }

}
