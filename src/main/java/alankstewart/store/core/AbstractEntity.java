package alankstewart.store.core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @Version
    private Integer version;

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object obj) {
        if (id == null || obj == null || !Objects.equals(getClass(), obj.getClass())) {
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
