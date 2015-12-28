package alankstewart.store.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Order web-server. Works as a microservice client, fetching data from the
 * Order-Service. Uses the Discovery Server (Eureka) to find the microservice.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class WebServer {

    public static final String ORDER_SERVICE_URL = "http://ORDER-SERVICE:2222";

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
        return new WebOrderController(orderService());
    }
}