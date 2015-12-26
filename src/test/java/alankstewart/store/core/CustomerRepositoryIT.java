package alankstewart.store.core;

import alankstewart.store.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class CustomerRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldSaveCustomer() {
        Customer customer = new Customer("Alan", "Stewart");
        customer.add(new Address("2 Jubilee Street", "Wahroonga", "Australia"));
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
