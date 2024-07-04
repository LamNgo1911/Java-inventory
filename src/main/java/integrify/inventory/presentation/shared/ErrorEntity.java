package integrify.inventory.presentation.shared;

import lombok.*;

import java.util.List;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEntity {
    private String field;
    private String message;
}

