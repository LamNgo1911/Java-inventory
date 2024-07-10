package integrify.inventory.application.dtos.order;

import integrify.inventory.application.dtos.orderItem.OrderItemMapper;
import integrify.inventory.application.dtos.orderItem.OrderItemReadDto;
import integrify.inventory.application.dtos.supplier.SupplierMapper;
import integrify.inventory.domain.model.Order;
import integrify.inventory.domain.model.OrderItem;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD, uses = { OrderItemMapper.class, SupplierMapper.class, OrderMapper.class })
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "orderItemCreateDtoList", target = "orderItems")
    @Mapping(source = "supplierId", target = "supplier.id")
    Order toOrder(OrderCreateDto orderCreateDto);

    @Mapping(source = "orderItems", target = "orderItemReadDtoList")
    @Mapping(source = "supplier.id", target = "supplierId")
    OrderReadDto toOrderReadDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    void updateOrderFromDto(OrderUpdateDto updateDto, @MappingTarget Order order);
}
