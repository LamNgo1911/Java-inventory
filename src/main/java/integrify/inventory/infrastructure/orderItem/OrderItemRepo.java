package integrify.inventory.infrastructure.orderItem;

import integrify.inventory.domain.repository.IOrderItemRepo;
import integrify.inventory.domain.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemRepo implements IOrderItemRepo {
    @Autowired
    private OrderItemJpaRepo _orderItemJpaRepo;

    @Override
    public OrderItem save(OrderItem orderItem) {
        return _orderItemJpaRepo.save(orderItem);
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return _orderItemJpaRepo.findById(id);
    }

    @Override
    public List<OrderItem> findAll() {
        return _orderItemJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
         _orderItemJpaRepo.deleteById(id);
    }
}
