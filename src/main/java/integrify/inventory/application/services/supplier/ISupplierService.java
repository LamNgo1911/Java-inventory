package integrify.inventory.application.services.supplier;

import integrify.inventory.application.dtos.supplier.SupplierCreateDto;
import integrify.inventory.application.dtos.supplier.SupplierReadDto;
import integrify.inventory.application.dtos.supplier.SupplierUpdateDto;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.model.Supplier;

import java.util.List;

public interface ISupplierService {
    public SupplierReadDto createSupplier(SupplierCreateDto supplierCreateDto);
    public SupplierReadDto updateSupplier(Long id, SupplierUpdateDto supplierDetails);
    public PaginationPage<SupplierReadDto> getAllSuppliers(int limit, int offset);
    public SupplierReadDto getSupplierById(Long id);
    public void deleteSupplier(Long id);
}
