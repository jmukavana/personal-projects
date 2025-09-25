package com.jamlech.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Discovery Service - Eureka Dashboard
                .route("discovery-service-web", r -> r.path("/eureka/web")
                        .filters(f -> f.setPath("/"))
                        .uri("http://localhost:8761"))

                // Discovery Service - All Eureka endpoints
                .route("discovery-service-static", r -> r.path("/eureka/**")
                        .uri("http://localhost:8761"))

                // Vendor Service Routes
                .route("vendor-service", r -> r.path("/api/v1/vendors/**")
                        .filters(f -> f
                                .stripPrefix(3) // Remove /api/v1/vendors
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .circuitBreaker(config -> config
                                        .setName("vendor-service-cb")
                                        .setFallbackUri("forward:/fallback/vendor")))
                        .uri("lb://vendor-service"))

                // Product Catalog Service Routes
                .route("product-service", r -> r.path("/api/v1/products/**")
                        .filters(f -> f
                                .stripPrefix(3) // Remove /api/v1/products
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .circuitBreaker(config -> config
                                        .setName("product-service-cb")
                                        .setFallbackUri("forward:/fallback/products")))
                        .uri("lb://product-service"))

                // Order Service Routes
                .route("order-service", r -> r.path("/api/v1/orders/**")
                        .filters(f -> f
                                .stripPrefix(3) // Remove /api/v1/orders
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .circuitBreaker(config -> config
                                        .setName("order-service-cb")
                                        .setFallbackUri("forward:/fallback/orders")))
                        .uri("lb://order-service"))

                // Payment Service Routes
                .route("payment-service", r -> r.path("/api/v1/payments/**")
                        .filters(f -> f
                                .stripPrefix(3) // Remove /api/v1/payments
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .addRequestHeader("X-Service-Type", "payment")
                                .circuitBreaker(config -> config
                                        .setName("payment-service-cb")
                                        .setFallbackUri("forward:/fallback/payments")))
                        .uri("lb://payment-service"))

                // User Service Routes
                .route("user-service", r -> r.path("/api/v1/users/**")
                        .filters(f -> f
                                .stripPrefix(3) // Remove /api/v1/users
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .circuitBreaker(config -> config
                                        .setName("user-service-cb")
                                        .setFallbackUri("forward:/fallback/users")))
                        .uri("lb://user-service"))

                // Authentication Routes
                .route("auth-service", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f
                                .stripPrefix(2) // Remove /api/v1, keep /auth
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .addRequestHeader("X-Service-Type", "authentication")
                                .circuitBreaker(config -> config
                                        .setName("auth-service-cb")
                                        .setFallbackUri("forward:/fallback/auth")))
                        .uri("lb://user-service"))

                // Inventory Service Routes
                .route("inventory-service", r -> r.path("/api/v1/inventory/**")
                        .filters(f -> f
                                .stripPrefix(3) // Remove /api/v1/inventory
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .circuitBreaker(config -> config
                                        .setName("inventory-service-cb")
                                        .setFallbackUri("forward:/fallback/inventory")))
                        .uri("lb://inventory-service"))

                // Notification Service Routes
                .route("notification-service", r -> r.path("/api/v1/notifications/**")
                        .filters(f -> f
                                .stripPrefix(3) // Remove /api/v1/notifications
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .circuitBreaker(config -> config
                                        .setName("notification-service-cb")
                                        .setFallbackUri("forward:/fallback/notifications")))
                        .uri("lb://notification-service"))

                // Admin Service Routes (for multivendor admin panel)
                .route("admin-service", r -> r.path("/api/v1/admin/**")
                        .filters(f -> f
                                .stripPrefix(3) // Remove /api/v1/admin
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .addRequestHeader("X-Service-Type", "admin")
                                .circuitBreaker(config -> config
                                        .setName("admin-service-cb")
                                        .setFallbackUri("forward:/fallback/admin")))
                        .uri("lb://admin-service"))

                // File Upload Service (for product images, documents)
                .route("file-service", r -> r.path("/api/v1/files/**")
                        .filters(f -> f
                                .stripPrefix(3) // Remove /api/v1/files
                                .addRequestHeader("X-Gateway-Name", "multivendor-gateway")
                                .addRequestHeader("X-Service-Type", "file-upload"))
                        .uri("lb://file-service"))

                // Health Check Route for all services
                .route("health-check", r -> r.path("/health/**")
                        .filters(f -> f.stripPrefix(1)) // Remove /health
                        .uri("lb://health-service"))

                .build();
    }

    // Optional: Custom filter for logging
    @Bean
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) -> {
            log.info("Custom Global Filter executed: " +
                    exchange.getRequest().getPath());
            return chain.filter(exchange);
        };
    }
}
