package com.lxl.utils.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 * @auther lixinlong
 * @create 2018/5/18
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

}
