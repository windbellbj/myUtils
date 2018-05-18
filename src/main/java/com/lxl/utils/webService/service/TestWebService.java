package com.lxl.utils.webService.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * webservice测试接口
 * @address http://localhost:8080/services/TestWebService?wsdl
 * @auther lixinlong
 * @create 2018/5/18
 */
@WebService(name = "TestWebService", // 服务名称
        targetNamespace = "http://webService.lxl.com"// 命名空间
)
public interface TestWebService {

    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String sendMessage(@WebParam(name = "username") String username);
}