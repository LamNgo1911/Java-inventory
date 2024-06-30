package integrify.inventory.domain.services;

import integrify.inventory.domain.repositories.orderItem.OrderItem;

import java.util.List;

public interface IOrderItemService {
    public OrderItem createOrderItem(OrderItem orderItem);
    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails);
    public List<OrderItem> getAllOrderItems();
    public OrderItem getOrderItemById(Long id);
    public void deleteOrderItem(Long id);
}
