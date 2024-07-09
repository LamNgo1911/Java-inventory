package integrify.inventory.infrastructure.supplier;

import integrify.inventory.domain.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupplierJpaRepo extends JpaRepository<Supplier, UUID> {
    public Optional<Supplier> findByEmail(String email);
}
