package io.github.abudanov.pubsub.subscriber.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "SUBSCRIPTION")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionEntity {

    @Id
    private Long id;

    @Column(name = "msisdn", nullable = false)
    private Long msisdn;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

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
