package alankstewart.store.core;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;

import static org.springframework.util.Assert.hasText;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "address_seq")
public class Address extends AbstractEntity {

    public enum State {ACT, NSW, NT, QLD, TAS, VIC, WA}

    private String street;
    private String suburb;

    @Enumerated(EnumType.STRING)
    private State state;

    private String postcode;

    public Address(String street, String suburb, State state, String postcode) {
        hasText(street, "Street must not be null or empty");
        hasText(suburb, "Suburb must not be null or empty");
        Assert.notNull(state, "State must not be null");
        hasText(postcode, "Postcode must not be null or empty");
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
    }

    protected Address() {
    }

    public Address getCopy() {
        return new Address(street, suburb, state, postcode);
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }

    public State getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    @Override
    public String toString() {
        return String.format("Address{%d, %s %s %s %s}", getId(), street, suburb, state, postcode);
    }
}
