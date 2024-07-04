package integrify.inventory.presentation.shared;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseEntity<T> {
    public List<T> data;
    public Object errors = null;
}
