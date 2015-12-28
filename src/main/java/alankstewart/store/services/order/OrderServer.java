package alankstewart.store.services.order;

import alankstewart.store.order.OrderWebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(OrderWebApplication.class)
public class OrderServer {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "order-server");
        SpringApplication.run(OrderServer.class, args);
    }
}
