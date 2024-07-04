package integrify.inventory.domain.repository;

import integrify.inventory.domain.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface IOrderItemRepo {
    OrderItem save(OrderItem orderItem);
    Optional<OrderItem> findById(Long id);
    List<OrderItem> findAll();
    void deleteById(Long id);
}
