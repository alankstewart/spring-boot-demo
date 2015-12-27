package alankstewart.store.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class WebServer {

    public static final String ORDER_SERVICE_URL = "http://ORDER-SERVICE";

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "web-server");
        SpringApplication.run(WebServer.class, args);
    }

    @Bean
    public WebOrderService orderService() {
        return new WebOrderService(ORDER_SERVICE_URL);
    }

    @Bean
    public WebOrderController orderController() {
        WebOrderController orderController = new WebOrderController();
        orderController.setWebOrderService(orderService());
        return orderController;
    }
}