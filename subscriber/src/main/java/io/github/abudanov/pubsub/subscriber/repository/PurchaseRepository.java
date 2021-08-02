package io.github.abudanov.pubsub.subscriber.repository;

import io.github.abudanov.pubsub.subscriber.entity.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CrudRepository<PurchaseEntity, Long> {
}
