package integrify.inventory.application.dtos.order;

import integrify.inventory.application.dtos.orderItem.OrderItemReadDto;
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
public class OrderReadDto {
    private UUID id;
    private Date orderDate;
    private String status;
    private Set<OrderItemReadDto> orderItems;
    private UUID supplierId;
}
