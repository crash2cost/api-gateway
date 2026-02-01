package io.github.mrlevi1112.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${services.auth.url}")
    private String authServiceUrl;

    @Value("${services.auth.name}")
    private String authServiceName;

    @Value("${services.report.url}")
    private String reportServiceUrl;

    @Value("${services.report.name}")
    private String reportServiceName;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(authServiceName, r -> r.path("/api/auth/**")
                        .uri(authServiceUrl))

                .route("image-service", r -> r.path("/api/images/**")
                        .uri(authServiceUrl))

                .route("assessment-service", r -> r.path("/api/assessments/**")
                        .uri(authServiceUrl))

                .route(reportServiceName, r -> r.path("/api/reports/**")
                        .uri(reportServiceUrl))

                .build();
    }
}
