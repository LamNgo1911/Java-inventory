package integrify.inventory.application.dtos.order;

import integrify.inventory.application.dtos.orderItem.OrderItemCreateDto;
import integrify.inventory.domain.OrderStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCreateDto {
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @NotNull
    private List<OrderItemCreateDto> orderItemCreateDtoList;

    @NotNull
    private UUID supplierId;
}
