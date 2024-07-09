package integrify.inventory.infrastructure.order;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.repository.IOrderRepo;
import integrify.inventory.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class OrderRepo implements IOrderRepo {

    @Autowired
    private OrderJpaRepo _orderJpaRepo;

    @Override
    public Order save(Order order) {
        return _orderJpaRepo.save(order);
    }

    @Override
    public void saveAll(List<Order> orders) {_orderJpaRepo.saveAll(orders);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return _orderJpaRepo.findById(id);
    }

    @Override
    public Page<Order> findAll(OffsetPage pageable) {
        return _orderJpaRepo.findAll(pageable);
    }

    @Override
    public void deleteById(UUID id) {
        _orderJpaRepo.deleteById(id);
    }
}
