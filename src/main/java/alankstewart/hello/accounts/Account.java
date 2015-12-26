package alankstewart.hello.accounts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Integer version;
}
