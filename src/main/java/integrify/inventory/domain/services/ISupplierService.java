package integrify.inventory.domain.services;

import integrify.inventory.domain.repositories.supplier.Supplier;

import java.util.List;

public interface ISupplierService {
    public Supplier createSupplier(Supplier supplier);
    public Supplier updateSupplier(Long id, Supplier supplierDetails);
    public List<Supplier> getAllSuppliers();
    public Supplier getSupplierById(Long id);
    public void deleteSupplier(Long id);
}
