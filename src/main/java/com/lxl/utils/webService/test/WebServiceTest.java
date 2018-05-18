package com.lxl.utils.webService.test;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

/**
 * @auther lixinlong
 * @create 2018/5/18
 */
public class WebServiceTest {

    @Test
    public void testSend1(){

        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/services/TestWebService?wsdl");

        // 需要密码的情况需要加上用户名和密码
        String username = "";
        String password = "";
        // client.getOutInterceptors().add(new ClientLoginInterceptor(username,password));
        Object[] objects = new Object[0];
        try {

//          objects = client.invoke("方法名",参数1,参数2,参数3....);   // TODO: 2018/5/18 参数顺序要保持一致
            objects = client.invoke("sendMessage", "lixinlong");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
