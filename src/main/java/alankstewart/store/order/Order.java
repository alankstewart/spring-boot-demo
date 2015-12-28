package alankstewart.store.order;

import alankstewart.store.core.AbstractEntity;
import alankstewart.store.core.Address;
import alankstewart.store.core.Customer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Orders")
@SequenceGenerator(name = "seq", sequenceName = "orders_seq")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Order extends AbstractEntity {

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne
    private Address billingAddress;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Address shippingAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private Set<LineItem> lineItems = new HashSet<>();

    public Order(Customer customer, Address shippingAddress) {
        this(customer, shippingAddress, null);
    }

    public Order(Customer customer, Address shippingAddress, Address billingAddress) {
        Assert.notNull(customer);
        Assert.notNull(shippingAddress);
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress == null ? null : billingAddress;
    }

    protected Order() {
    }

    public void add(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getBillingAddress() {
        return billingAddress == null ? shippingAddress : billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Set<LineItem> getLineItems() {
        return Collections.unmodifiableSet(lineItems);
    }

    public BigDecimal getTotal() {
        return lineItems.stream()
                .map(LineItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
