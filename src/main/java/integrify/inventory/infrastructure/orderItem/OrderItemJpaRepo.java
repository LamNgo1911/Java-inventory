package integrify.inventory.infrastructure.orderItem;

import integrify.inventory.domain.repositories.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemJpaRepo extends JpaRepository<OrderItem, Long> {}