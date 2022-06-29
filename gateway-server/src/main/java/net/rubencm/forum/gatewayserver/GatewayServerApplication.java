package net.rubencm.forum.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/forums/**")
                        .filters(f ->
                                f.rewritePath("^/forums/", "/"))
                        .uri("lb://forums-service")
                )
                .route(p -> p
                        .path("/topics/**")
                        .filters(f ->
                                f.rewritePath("^/topics/", "/"))
                        .uri("lb://topics-service")
                )
                .route(p -> p
                        .path("/messages/**")
                        .filters(f ->
                                f.rewritePath("^/messages/", "/"))
                        .uri("lb://messages-service")
                )
                .build();
    }
}
