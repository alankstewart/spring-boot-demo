package alankstewart.store.order;

import alankstewart.store.AbstractIntegrationTest;
import alankstewart.store.core.Customer;
import alankstewart.store.core.CustomerRepository;
import alankstewart.store.core.EmailAddress;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OrderRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldFindAllOrders() {
        assertThat(orderRepository.count(), is(1L));
    }

    @Test
    public void shouldFindOrderByCustomer() {
        Customer customer = customerRepository.findByEmailAddress(new EmailAddress("alankstewart@gmail.com"));
        assertThat(customer, is(notNullValue()));
        List<Order> orders = orderRepository.findByCustomer(customer);
        assertThat(orders, hasSize(1));
    }
}
