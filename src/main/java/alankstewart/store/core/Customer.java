package alankstewart.store.core;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.util.Assert.hasText;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "customer_seq")
public class Customer extends AbstractEntity {

    private String firstname;
    private String lastname;

    @Column(unique = true)
    private EmailAddress emailAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private Set<Address> addresses = new HashSet<>();

    public Customer(String firstname, String lastname) {
        hasText(firstname);
        hasText(lastname);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    protected Customer() {
    }

    public void add(Address address) {
        Assert.notNull(address);
        addresses.add(address);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }
}
