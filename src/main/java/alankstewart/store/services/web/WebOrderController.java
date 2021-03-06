package alankstewart.store.services.web;

import alankstewart.store.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Client controller, fetches Order info from the microservice via
 * {@link WebOrderService}.
 */
@Controller
public class WebOrderController {

    @Autowired
    protected WebOrderService orderService;

    private Logger logger = Logger.getLogger(WebOrderController.class.getName());

    public WebOrderController(WebOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order", method = GET)
    public String goHome() {
        return "index";
    }

    @RequestMapping(value = "/order/{id}", method = GET)
    public String byId(Model model, @PathVariable("id") Long id) {
        logger.info("web-service byId() invoked: " + id);
        Order order = orderService.getById(id);
        logger.info("web-service byId() found: " + order);
        model.addAttribute("order", order);
        return "order";
    }
}
