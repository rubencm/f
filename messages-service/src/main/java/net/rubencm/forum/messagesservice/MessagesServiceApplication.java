package net.rubencm.forum.messagesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@ComponentScan({"net.rubencm.forum.messagesservice", "net.rubencm.forum.shared"})
public class MessagesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesServiceApplication.class, args);
    }

}
