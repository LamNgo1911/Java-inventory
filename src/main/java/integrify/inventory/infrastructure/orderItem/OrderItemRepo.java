package integrify.inventory.infrastructure.orderItem;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.repository.IOrderItemRepo;
import integrify.inventory.domain.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Page<OrderItem> findAll(OffsetPage pageable) {
        return _orderItemJpaRepo.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
         _orderItemJpaRepo.deleteById(id);
    }

    @Override
    public void saveAll(Set<OrderItem> orderItems) {
        _orderItemJpaRepo.saveAll(orderItems);
    }
}
