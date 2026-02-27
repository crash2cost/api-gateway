package io.github.mrlevi1112.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOriginPatterns(List.of("http://localhost:*", "http://127.0.0.1:*"));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfig.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept", "Origin", "X-Requested-With"));
        corsConfig.setExposedHeaders(List.of("Authorization", "Content-Type"));
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public WebFilter deduplicateCorsHeadersFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                var headers = exchange.getResponse().getHeaders();
                if (headers.containsKey("Access-Control-Allow-Origin")) {
                    List<String> origins = headers.get("Access-Control-Allow-Origin");
                    if (origins != null && origins.size() > 1) {
                        headers.set("Access-Control-Allow-Origin", origins.get(0));
                    }
                }
            }));
        };
    }
}
