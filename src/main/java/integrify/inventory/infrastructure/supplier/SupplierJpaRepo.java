package integrify.inventory.infrastructure.supplier;

import integrify.inventory.domain.repositories.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierJpaRepo extends JpaRepository<Supplier, Long> {}
