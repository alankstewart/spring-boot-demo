package alankstewart.store.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/order/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public Order byId(@PathVariable("id") Long id) {
        Order order = orderRepository.findOne(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        } else {
            return order;
        }
    }

    @RequestMapping(value = "/order/all", method = GET, produces = APPLICATION_JSON_VALUE)
    public List<Order> orders() {
        return orderRepository.findAll();
    }
}
