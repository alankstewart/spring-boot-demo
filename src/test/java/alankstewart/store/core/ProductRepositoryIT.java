package alankstewart.store.core;

import alankstewart.store.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
@Transactional
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldFindAllProducts() {
        assertThat(productRepository.count(), is(3L));
    }

    @Test
    public void shouldFindProductByDescription() {
        PageRequest request = new PageRequest(0, 10, Sort.Direction.ASC, "id");
        Page<Product> products = productRepository.findByDescriptionContaining("Apple", request);
        assertThat(products, is(notNullValue()));
        assertThat(products.getTotalPages(), is(1));
        assertThat(products.getTotalElements(), is(2L));
    }

    @Test
    public void shouldFindProductByAttribute() {
        Product product = new Product("iPhone", new BigDecimal("1000"), "iPhone 6S Plus 64Gb");
        product.setAttribute("case", "iPhone 5s Case - Black");
        product.setAttribute("screen protector", "Power Support Anti-Glare Film");
        product = productRepository.save(product);
        assertThat(product, is(notNullValue()));
        List<Product> products = productRepository.findByAttributeAndValue("case", "iPhone 5s Case - Black");
        assertThat(products, hasSize(1));
        product = products.get(0);
        assertThat(product.getName(), is("iPhone"));
    }
}
