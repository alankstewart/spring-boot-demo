package alankstewart.store.core;

import alankstewart.store.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
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

    @Test
    public void shouldFindProductByAttribute() {
        Product product = new Product("iPhone", new BigDecimal("1000"), "iPhone 6S Plus 64Gb");
        product.setAttribute("case", "black leather");
        product = productRepository.save(product);
        assertThat(product, is(notNullValue()));
        System.out.println(product);
        List<Product> products = productRepository.findByAttributeAndValue("case", "black leather");
        assertThat(products, hasSize(1));
        product = products.get(0);
        assertThat(product.getName(), is("iPhone"));
    }
}
