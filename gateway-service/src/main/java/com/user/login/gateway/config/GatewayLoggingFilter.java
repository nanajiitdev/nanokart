package com.user.login.gateway.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
	public class GatewayLoggingFilter implements GlobalFilter {

	    private static final Logger log =
	            LoggerFactory.getLogger(GatewayLoggingFilter.class);

	    @Override
	    public Mono<Void> filter(
	            ServerWebExchange exchange,
	            GatewayFilterChain chain) {

	        String path =
	                exchange.getRequest().getURI().getPath();

	        String method =
	                exchange.getRequest().getMethod().name();

	        log.info(
	                "GATEWAY REQUEST method={} path={}",
	                method,
	                path
	        );

	        return chain.filter(exchange)
	                .doOnSuccess(v ->
	                        log.info(
	                                "GATEWAY RESPONSE status={} path={}",
	                                exchange.getResponse().getStatusCode(),
	                                path
	                        )
	                );
	    }
	}
