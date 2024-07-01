package integrify.inventory.application.dtos.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockCreateDto {
    private UUID productId;
    private int quantity;
    private UUID supplierId;
}
