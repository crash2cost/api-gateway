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
        final String corsHeadersToDedupe = "Access-Control-Allow-Origin Access-Control-Allow-Credentials Access-Control-Expose-Headers";

        return builder.routes()
                .route(authServiceName, r -> r.path("/api/auth/**")
                        .filters(f -> f.dedupeResponseHeader(corsHeadersToDedupe, "RETAIN_FIRST"))
                        .uri(authServiceUrl))

                .route(authServiceName + "-images", r -> r.path("/api/images/**")
                        .filters(f -> f.dedupeResponseHeader(corsHeadersToDedupe, "RETAIN_FIRST"))
                        .uri(authServiceUrl))

                .route(authServiceName + "-assessments", r -> r.path("/api/assessments/**")
                        .filters(f -> f.dedupeResponseHeader(corsHeadersToDedupe, "RETAIN_FIRST"))
                        .uri(authServiceUrl))

                .route(authServiceName + "-admin", r -> r.path("/api/admin/**")
                        .filters(f -> f.dedupeResponseHeader(corsHeadersToDedupe, "RETAIN_FIRST"))
                        .uri(authServiceUrl))

                .route(reportServiceName, r -> r.path("/api/reports/**")
                        .filters(f -> f.dedupeResponseHeader(corsHeadersToDedupe, "RETAIN_FIRST"))
                        .uri(reportServiceUrl))

                .build();
    }
}
