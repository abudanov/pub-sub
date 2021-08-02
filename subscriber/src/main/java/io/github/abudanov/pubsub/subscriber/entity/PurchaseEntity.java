package io.github.abudanov.pubsub.subscriber.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PURCHASE")
@Setter
@Getter
@ToString
public class PurchaseEntity {
    @Id
    private Long id;

    @Column(name = "msisdn", nullable = false)
    private Long msisdn;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    public PurchaseEntity(
            Long id,
            Long msisdn,
            Timestamp timestamp
    ) {
        this.id = id;
        this.msisdn = msisdn;
        this.timestamp = timestamp;
    }

    public PurchaseEntity() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PurchaseEntity entity = (PurchaseEntity) o;

        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
