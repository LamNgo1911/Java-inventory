package integrify.inventory.application.services.supplier;

import integrify.inventory.domain.model.Supplier;

import java.util.List;

public interface ISupplierService {
    public Supplier createSupplier(Supplier supplier);
    public Supplier updateSupplier(Long id, Supplier supplierDetails);
    public List<Supplier> getAllSuppliers();
    public Supplier getSupplierById(Long id);
    public void deleteSupplier(Long id);
}
