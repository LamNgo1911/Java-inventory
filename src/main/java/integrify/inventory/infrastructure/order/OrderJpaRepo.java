package integrify.inventory.infrastructure.order;

import integrify.inventory.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderJpaRepo extends JpaRepository<Order, UUID> {
}