package alankstewart.store.core;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import static org.springframework.util.Assert.hasText;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "address_seq")
public class Address extends AbstractEntity {

    private String street;
    private String city;
    private String country;

    public Address(String street, String city, String country) {
        hasText(street, "Street must not be null or empty!");
        hasText(city, "City must not be null or empty!");
        hasText(country, "Country must not be null or empty!");
        this.street = street;
        this.city = city;
        this.country = country;
    }

    protected Address() {
    }

    public Address getCopy() {
        return new Address(street, city, country);
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return String.format("Address{%d, %s, %s, %s}", getId(), street, city, country);
    }
}
