package integrify.inventory.application.services.orderItem;

import integrify.inventory.domain.repositories.orderItem.IOrderItemRepo;
import integrify.inventory.domain.repositories.orderItem.OrderItem;
import integrify.inventory.domain.services.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService {
    @Autowired
    private IOrderItemRepo _IOrderItemRepo;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return _IOrderItemRepo.save(orderItem);
    }

    @Override
    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails) {
        return null;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return List.of();
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return null;
    }

    @Override
    public void deleteOrderItem(Long id) {

    }
}
