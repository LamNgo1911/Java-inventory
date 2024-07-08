package integrify.inventory.application.dtos.orderItem;

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
public class OrderItemCreateDto {
    @NotNull
    @Min(value = 0)
    private int quantity;

    @NotNull
    private UUID productId;
}
