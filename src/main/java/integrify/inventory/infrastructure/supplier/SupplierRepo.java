package integrify.inventory.infrastructure.supplier;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.repository.ISupplierRepo;
import integrify.inventory.domain.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<Supplier> findAll(OffsetPage pageable) {
        return _supplierJpaRepo.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        _supplierJpaRepo.deleteById(id);
    }

    @Override
    public Optional<Supplier> findByEmail(String email) {
        return _supplierJpaRepo.findByEmail(email);
    }
}
