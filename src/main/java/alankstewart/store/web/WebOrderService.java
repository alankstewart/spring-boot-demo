package alankstewart.store.web;

import alankstewart.store.order.Order;
import alankstewart.store.order.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebOrderService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    public WebOrderService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public WebOrderService() {
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
