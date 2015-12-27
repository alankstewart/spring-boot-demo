package alankstewart.store.services.order;

import alankstewart.store.ApplicationConfig;
import alankstewart.store.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(ApplicationConfig.class)
public class OrderServer {

    @Autowired
    private OrderRepository ordderRepository;

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "order-server");
        SpringApplication.run(OrderServer.class, args);
    }
}
