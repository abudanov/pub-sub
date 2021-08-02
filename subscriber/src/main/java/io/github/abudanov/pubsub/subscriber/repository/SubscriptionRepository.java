package io.github.abudanov.pubsub.subscriber.repository;

import io.github.abudanov.pubsub.subscriber.entity.SubscriptionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Long> {
}
