package integrify.inventory.application.services.supplier;

import integrify.inventory.application.dtos.supplier.SupplierCreateDto;
import integrify.inventory.application.dtos.supplier.SupplierReadDto;
import integrify.inventory.application.dtos.supplier.SupplierUpdateDto;
import integrify.inventory.application.shared.PaginationPage;

import java.util.UUID;

public interface ISupplierService {
    public SupplierReadDto createSupplier(SupplierCreateDto supplierCreateDto);
    public SupplierReadDto updateSupplier(UUID id, SupplierUpdateDto supplierDetails);
    public PaginationPage<SupplierReadDto> getAllSuppliers(int limit, int offset);
    public SupplierReadDto getSupplierById(UUID id);
    public void deleteSupplier(UUID id);
}
