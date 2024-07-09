package integrify.inventory.domain.repository;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderRepo {
    public Order save(Order order);
    void saveAll(List<Order> orders);
    public Optional<Order> findById(UUID id);
    public Page<Order> findAll(OffsetPage pageable);
    public void deleteById(UUID id);
}
