package integrify.inventory.application.dtos.stock;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockReadDto {
    @NotNull
    private UUID id;

    @NotNull
    private UUID productId;

    @NotNull
    @Min(value = 0)
    private int quantity;

    @NotNull
    private UUID supplierId;
}
