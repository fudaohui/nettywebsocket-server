package com.fdh.websocket.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WebsocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketServerApplication.class, args);
        log.info("开始启动");
        WebSocketServer nettyServer = SpringApplicationContextUtils.getBean(WebSocketServer.class);
        nettyServer.start();
    }

}
