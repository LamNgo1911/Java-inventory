package integrify.inventory.application.services.order;

import integrify.inventory.application.dtos.order.OrderCreateDto;
import integrify.inventory.application.dtos.order.OrderReadDto;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public interface IOrderService {
    public OrderReadDto createOrder(OrderCreateDto orderCreateDto);
    public OrderReadDto updateOrderStatus(UUID id, OrderStatusEnum status);
    public PaginationPage<OrderReadDto> getAllOrders(int limit, int offset);
    public OrderReadDto getOrderById(UUID id);
    public void cancelOrder(UUID id);
    public List<OrderReadDto> getOrdersByCurrentDay();
    public List<OrderReadDto> getOrdersByCurrentWeek();
    public List<OrderReadDto> getOrdersByCurrentMonth();
}
