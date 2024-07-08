package integrify.inventory.application.dtos.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class SupplierReadDto {
    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
