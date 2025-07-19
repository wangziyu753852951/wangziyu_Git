package com.example.java_gobang2.config;

import com.example.java_gobang2.api.GameAPI;
import com.example.java_gobang2.api.MatchAPI;
import com.example.java_gobang2.api.TestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * ClassName: WebSocketConfig
 * Description
 *
 * @Author wzy
 * @Create 2025/1/24 13:03
 * @Version 1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private TestAPI testAPI;

    @Autowired
    private MatchAPI matchAPI;

    @Autowired
    private GameAPI gameAPI;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(testAPI,"/test")
                .setAllowedOrigins("*");  //跨域问题 加上这个配置就行了
        webSocketHandlerRegistry.addHandler(matchAPI,"/findMatch")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
        webSocketHandlerRegistry.addHandler(gameAPI,"/game")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }

}
