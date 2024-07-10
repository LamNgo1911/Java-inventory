package integrify.inventory.application.dtos.order;

import integrify.inventory.application.dtos.orderItem.OrderItemReadDto;
import integrify.inventory.domain.OrderStatusEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderReadDto {
    @NotNull
    private UUID id;

    @NotNull
    private Date orderDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @NotNull
    private List<OrderItemReadDto> orderItemReadDtoList;

    @NotNull
    private UUID supplierId;
}
