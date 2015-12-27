package alankstewart.store.core;

import alankstewart.store.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
@Transactional
public class CustomerRepositoryIT  {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldSaveCustomer() {
        Customer customer = new Customer("Alan", "Stewart");
        customer.add(new Address("2 Jubilee Street", "Wahroonga", Address.State.NSW, "2076"));
        EmailAddress emailAddress = new EmailAddress("alankstewart@optusnet.com.au");
        customer.setEmailAddress(emailAddress);
        customerRepository.save(customer);
        customer = customerRepository.findByEmailAddress(emailAddress);
        assertThat(customer, is(notNullValue()));
        assertThat(customerRepository.count(), is(4L));
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(System.out::println);
    }
}
