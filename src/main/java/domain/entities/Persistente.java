package domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persistente {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }
}
