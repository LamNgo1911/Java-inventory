package integrify.inventory.domain.repository;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.model.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ISupplierRepo {
    Supplier save(Supplier supplier);
    Optional<Supplier> findById(Long id);
    Page<Supplier> findAll(OffsetPage pageable);
    void deleteById(Long id);
    Optional<Supplier> findByEmail(String email);
}