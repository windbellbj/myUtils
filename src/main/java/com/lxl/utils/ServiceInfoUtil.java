package com.lxl.utils;

/**
 * @auther lixinlong
 * @create 2018/5/18
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ServiceInfoUtil implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    private static EmbeddedServletContainerInitializedEvent event;
    protected final static Logger logger = LoggerFactory.getLogger(ServiceInfoUtil.class);

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        ServiceInfoUtil.event = event;
        try {
            logger.info("\n----------------------------------------------------------\n\t" +
                    "Application At http://"+ "127.0.0.1"+":"+event.getEmbeddedServletContainer().getPort()+"\n\t\t"+
                    "LAN At http://"+ InetAddress.getLocalHost().getHostAddress()+":"+event.getEmbeddedServletContainer().getPort()
                    +"\n----------------------------------------------------------\n");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
