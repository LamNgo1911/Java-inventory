package integrify.inventory.application.dtos.orderItem;

import integrify.inventory.domain.model.OrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface OrderItemMapper{
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toOrderItem(OrderItemCreateDto orderItemCreateDto);

    OrderItemReadDto toOrderItemReadDto(OrderItem orderItem);

    void updateOrderItemFromDto(OrderItemUpdateDto updateDto, @MappingTarget OrderItem orderItem);
}
