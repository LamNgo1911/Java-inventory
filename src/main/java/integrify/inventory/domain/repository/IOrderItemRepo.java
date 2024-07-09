package integrify.inventory.domain.repository;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.model.OrderItem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IOrderItemRepo {
    OrderItem save(OrderItem orderItem);
    Optional<OrderItem> findById(UUID id);
    Page<OrderItem> findAll(OffsetPage pageable);
    void deleteById(UUID id);
    void saveAll(Set<OrderItem> orderItems);
}
