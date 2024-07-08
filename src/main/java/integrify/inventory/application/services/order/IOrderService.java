package integrify.inventory.application.services.order;

import integrify.inventory.application.dtos.order.OrderCreateDto;
import integrify.inventory.application.dtos.order.OrderReadDto;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.OrderStatusEnum;
import integrify.inventory.domain.model.Order;

import java.util.List;

public interface IOrderService {
    public OrderReadDto createOrder(OrderCreateDto orderCreateDto);
    public OrderReadDto updateOrderStatus(Long id, OrderStatusEnum status);
    public PaginationPage<OrderReadDto> getAllOrders(int limit, int offset);
    public OrderReadDto getOrderById(Long id);
    public void cancelOrder(Long id);
}
