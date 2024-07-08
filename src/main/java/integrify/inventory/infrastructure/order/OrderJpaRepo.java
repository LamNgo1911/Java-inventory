package integrify.inventory.infrastructure.order;

import integrify.inventory.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepo extends JpaRepository<Order, Long> {
}