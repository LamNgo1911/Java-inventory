package integrify.inventory.application.dtos.order;

import integrify.inventory.application.dtos.orderItem.OrderItemReadDto;
import integrify.inventory.domain.repositories.order.Order;
import integrify.inventory.domain.repositories.orderItem.OrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Order toOrder(OrderCreateDto orderCreateDto);

    OrderReadDto toOrderReadDto(Order order);

    void updateOrderFromDto(OrderUpdateDto updateDto, @MappingTarget Order order);

    default Set<OrderItemReadDto> mapOrderItemsToDto(Set<OrderItem> orderItems) {
        if (orderItems == null) {
            return null;
        }
        return orderItems.stream()
                .map(this::mapOrderItemToDto)
                .collect(Collectors.toSet());
    }

    OrderItemReadDto mapOrderItemToDto(OrderItem orderItem);
}
