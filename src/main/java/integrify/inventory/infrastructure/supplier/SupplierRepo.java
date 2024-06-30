package integrify.inventory.infrastructure.supplier;

import integrify.inventory.domain.repositories.supplier.ISupplierRepo;
import integrify.inventory.domain.repositories.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SupplierRepo implements ISupplierRepo {
    @Autowired
    private SupplierJpaRepo _supplierJpaRepo;

    @Override
    public Supplier save(Supplier supplier) {
        return _supplierJpaRepo.save(supplier);
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return _supplierJpaRepo.findById(id);
    }

    @Override
    public List<Supplier> findAll() {
        return _supplierJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        _supplierJpaRepo.deleteById(id);
    }
}
