package integrify.inventory.infrastructure.orderItem;

import integrify.inventory.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemJpaRepo extends JpaRepository<OrderItem, UUID> {
}