package integrify.inventory.application.dtos.order;

import integrify.inventory.application.dtos.orderItem.OrderItemCreateDto;
import integrify.inventory.domain.OrderStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCreateDto {
    @NotNull
    private Date orderDate;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Valid
    private Set<OrderItemCreateDto> orderItems;

    @NotNull
    private UUID supplierId;
}
