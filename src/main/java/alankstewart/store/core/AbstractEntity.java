package alankstewart.store.core;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="seq")
     private Long id;

    @Version
    private Integer version;

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (id == null || obj == null || !(getClass().equals(obj.getClass()))) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        AbstractEntity rhs = (AbstractEntity) obj;
        return Objects.equals(id, rhs.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
