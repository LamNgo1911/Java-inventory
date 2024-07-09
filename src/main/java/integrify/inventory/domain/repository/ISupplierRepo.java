package integrify.inventory.domain.repository;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.model.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISupplierRepo {
    Supplier save(Supplier supplier);
    Optional<Supplier> findById(UUID id);
    Page<Supplier> findAll(OffsetPage pageable);
    void deleteById(UUID id);
    Optional<Supplier> findByEmail(String email);
}