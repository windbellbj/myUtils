package com.lxl.utils.webService.impl;

import com.lxl.utils.webService.service.TestWebService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @auther lixinlong
 * @create 2018/5/18
 */
@WebService(serviceName = "TestWebService", // 与接口中指定的name一致
        targetNamespace = "http://webService.lxl.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.lxl.utils.webService.service.TestWebService"// 接口地址
)
@Component
public class TestWebServiceImpl implements TestWebService {

    @Override
    public String sendMessage(String username) {

        return "hello "+username;
    }

}