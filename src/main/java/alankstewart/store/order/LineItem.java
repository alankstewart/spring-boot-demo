package alankstewart.store.order;

import alankstewart.store.core.AbstractEntity;
import alankstewart.store.core.Product;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "line_item_seq")
public class LineItem extends AbstractEntity {

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private BigDecimal price;

    private int amount;

    public LineItem(Product product) {
        this(product, 1);
    }

    public LineItem(Product product, int amount) {
        Assert.notNull(product, "The given product must not be null");
        Assert.isTrue(amount > 0, "The amount of products to be bought must be greater than 0");
        this.product = product;
        this.amount = amount;
        price = this.product.getPrice();
    }

    public LineItem() {
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getUnitPrice() {
        return price;
    }

    public BigDecimal getTotal() {
        return price.multiply(BigDecimal.valueOf(amount));
    }
}
