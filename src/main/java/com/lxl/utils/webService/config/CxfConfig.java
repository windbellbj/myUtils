package com.lxl.utils.webService.config;

import javax.xml.ws.Endpoint;

import com.lxl.utils.webService.service.TestWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @auther lixinlong
 * @create 2018/5/18
 */
@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private TestWebService testWebService;

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, testWebService);
        endpoint.publish("/TestWebService");
        return endpoint;
    }
}
