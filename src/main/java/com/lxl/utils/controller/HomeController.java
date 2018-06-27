package com.lxl.utils.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面跳转
 * @auther lixinlong
 * @create 2018/5/18
 */
@Api(value="登陆跳转页面",description="登陆",tags={"用户首页"})
@Controller
@RequestMapping("/")
public class HomeController {

    @ApiOperation("获取用户首页信息")
    @GetMapping("/index")
    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
