package integrify.inventory.application.dtos.orderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderItemReadDto {
    private UUID id;
    private int quantity;
    private UUID productId;
}
