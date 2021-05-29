package com.hdm.monopoly.backend.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/client", "/queue/");
		config.setApplicationDestinationPrefixes("/server");
	}

	//default configuration to establish the connection.
	/*
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws-monopoly").setAllowedOriginPatterns("*").withSockJS();
	}

	 */

	//default configuration + sessionId of each player to establish the connection.
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {

		registry
				.addEndpoint("/ws-monopoly")
				.setAllowedOriginPatterns("*")
				.setHandshakeHandler(new DefaultHandshakeHandler() {

					public boolean beforeHandshake(
							ServerHttpRequest request,
							ServerHttpResponse response,
							WebSocketHandler wsHandler,
							Map attributes) throws Exception {

						if (request instanceof ServletServerHttpRequest) {
							ServletServerHttpRequest servletRequest
									= (ServletServerHttpRequest) request;
							HttpSession session = servletRequest
									.getServletRequest().getSession();
							attributes.put("sessionId", session.getId());
						}
						return true;
					}}).withSockJS();
	}
}

