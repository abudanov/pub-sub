package io.github.abudanov.pubsub.subscriber.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "SUBSCRIPTION")
@Setter
@Getter
public class SubscriptionEntity {
    @Id
    private Long id;

    @Column(name = "msisdn", nullable = false)
    private Long msisdn;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    public SubscriptionEntity() {}

    public SubscriptionEntity(
            Long id,
            Long msisdn,
            Timestamp timestamp
    ) {

        this.id = id;
        this.msisdn = msisdn;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubscriptionEntity entity = (SubscriptionEntity) o;

        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
