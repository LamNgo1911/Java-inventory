package integrify.inventory.infrastructure.order;

import integrify.inventory.domain.repositories.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepo extends JpaRepository<Order, Long> {}