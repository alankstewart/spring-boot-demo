package alankstewart.store.core;

import alankstewart.store.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductRepositoryIT extends AbstractIntegrationTest {

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
}
