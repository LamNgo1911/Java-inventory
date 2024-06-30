package integrify.inventory.application.dtos.supplier;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SupplierReadDto {
    private UUID id;
    private String name;
    private String email;
}
