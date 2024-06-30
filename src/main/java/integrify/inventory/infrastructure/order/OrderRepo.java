package integrify.inventory.infrastructure.order;

import integrify.inventory.domain.repositories.order.IOrderRepo;
import integrify.inventory.domain.repositories.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class OrderRepo implements IOrderRepo {

    @Autowired
    private OrderJpaRepo _orderJpaRepo;

    @Override
    public Order save(Order order) {
        return _orderJpaRepo.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return _orderJpaRepo.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return _orderJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        _orderJpaRepo.deleteById(id);
    }
}
