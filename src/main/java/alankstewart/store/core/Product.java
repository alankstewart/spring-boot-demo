package alankstewart.store.core;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "product_seq")
public class Product extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ElementCollection
    private Map<String, String> attributes = new HashMap<>();

    public Product(String name, BigDecimal price) {
        this(name, price, null);
    }

    public Product(String name, BigDecimal price, String description) {
        Assert.hasText(name, "Name must not be null or empty");
        Assert.isTrue(BigDecimal.ZERO.compareTo(price) == -1, "Price must be greater than zero");
        this.name = name;
        this.price = price;
        this.description = description;
    }

    protected Product() {
    }

    public void setAttribute(String name, String value) {
        Assert.hasText(name);
        if (value == null) {
            attributes.remove(name);
        } else {
            attributes.put(name, value);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }
}
