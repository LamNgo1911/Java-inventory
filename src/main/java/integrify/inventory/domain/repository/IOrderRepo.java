package integrify.inventory.domain.repository;

import integrify.inventory.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepo {
    public Order save(Order order);
    public Optional<Order> findById(Long id);
    public List<Order> findAll();
    public void deleteById(Long id);
}
