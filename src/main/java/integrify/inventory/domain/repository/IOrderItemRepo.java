package integrify.inventory.domain.repository;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.model.OrderItem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IOrderItemRepo {
    OrderItem save(OrderItem orderItem);
    Optional<OrderItem> findById(Long id);
    Page<OrderItem> findAll(OffsetPage pageable);
    void deleteById(Long id);
    void saveAll(Set<OrderItem> orderItems);
}
