package integrify.inventory.application.dtos.order;

import integrify.inventory.application.dtos.orderItem.OrderItemUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderUpdateDto {
    private String status;
    private Set<OrderItemUpdateDto> orderItems;
    private UUID supplierId;
}
