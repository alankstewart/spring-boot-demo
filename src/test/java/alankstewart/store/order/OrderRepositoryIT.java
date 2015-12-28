package alankstewart.store.order;

import alankstewart.store.TestConfiguration;
import alankstewart.store.core.Customer;
import alankstewart.store.core.CustomerRepository;
import alankstewart.store.core.EmailAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
@Transactional
public class OrderRepositoryIT {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldFindId() {
        assertThat(orderRepository.getOne(1L), is(notNullValue()));
    }

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
        Order order = orders.get(0);
        assertThat(order.getTotal(), is(closeTo(new BigDecimal("2297.00"), BigDecimal.ZERO)));
    }
}
