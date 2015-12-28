package alankstewart.store.web;

import alankstewart.store.order.Order;
import alankstewart.store.order.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * Hide the access to the microservice inside this local service.
 */
@Service
public class WebOrderService {

    private Logger logger = Logger.getLogger(WebOrderService.class.getName());

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    public WebOrderService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    /**
     * The RestTemplate works because it uses a custom request-factory that uses
     * Ribbon to look-up the service to use. This method simply exists to show this.
     */
    @PostConstruct
    public void demoOnly() {
        // Can't do this in the constructor because the RestTemplate injection happens afterwards.
        logger.warning("The RestTemplate request factory is " + restTemplate.getRequestFactory());
    }

    public Order getById(Long id) {
        Order order = restTemplate.getForObject(serviceUrl + "/order/{id}", Order.class, id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        } else {
            return order;
        }
    }
}
