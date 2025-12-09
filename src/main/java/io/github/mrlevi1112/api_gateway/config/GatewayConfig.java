package io.github.mrlevi1112.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import io.github.mrlevi1112.api_gateway.common.config;

public class GatewayConfig {
    @Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
            .route(config.AUTH_SERVICE_NAME, r -> r.path("/api/auth/**")
                    .uri(config.AUTH_SERVICE_URL))
            
            .route(config.REPORT_SERVICE_NAME, r -> r.path("/api/reports/**")
                    .uri(config.REPORT_SERVICE_URL))
            
            .build();
}
}
