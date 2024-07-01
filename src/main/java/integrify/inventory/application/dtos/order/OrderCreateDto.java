package integrify.inventory.application.dtos.order;

import integrify.inventory.application.dtos.orderItem.OrderItemCreateDto;
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
    private Date orderDate;
    private String status;
    private Set<OrderItemCreateDto> orderItems;
    private UUID supplierId;

}
