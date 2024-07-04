package integrify.inventory.application.services.order;

import integrify.inventory.domain.model.Order;

import java.util.List;

public interface IOrderService {
    public Order createOrder(Order order);
    public Order updateOrderStatus(Long id, String status);
    public List<Order> getAllOrders();
    public Order getOrderById(Long id);
    public void cancelOrder(Long id);
}
