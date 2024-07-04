package integrify.inventory.domain.repository;

import integrify.inventory.domain.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierRepo {
    Supplier save(Supplier supplier);
    Optional<Supplier> findById(Long id);
    List<Supplier> findAll();
    void deleteById(Long id);
}