package integrify.inventory.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @Column(columnDefinition = "VARCHAR(100)",nullable = false)
    @NotBlank(message = "Name must not be blank.")
    private String name;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false, unique = true)
    @NotBlank(message = "Email must not be blank.")
    @Email(message = "Invalid email format.")
    private String email;
}
