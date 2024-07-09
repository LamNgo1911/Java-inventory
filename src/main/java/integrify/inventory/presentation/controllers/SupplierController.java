package integrify.inventory.presentation.controllers;

import integrify.inventory.application.dtos.supplier.SupplierCreateDto;
import integrify.inventory.application.dtos.supplier.SupplierReadDto;
import integrify.inventory.application.dtos.supplier.SupplierUpdateDto;
import integrify.inventory.application.services.supplier.ISupplierService;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.presentation.shared.SuccessResponseEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    @Autowired
    private ISupplierService _supplierService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SupplierReadDto> createSupplier(@RequestBody @Valid SupplierCreateDto supplierCreateDto) {
        // Implement logic to create a new supplier
        SupplierReadDto supplierReadDto = _supplierService.createSupplier(supplierCreateDto);
        // Return the created supplier as SupplierReadDto
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SupplierReadDto> updateSupplier(@PathVariable UUID id, @RequestBody @Valid SupplierUpdateDto supplierDetails) {
        // Implement logic to update an existing supplier
        SupplierReadDto supplierReadDto = _supplierService.updateSupplier(id, supplierDetails);
        // Return the updated supplier as SupplierReadDto
        return ResponseEntity.status(HttpStatus.OK).body(supplierReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<SuccessResponseEntity<SupplierReadDto>> getAllSuppliers(@RequestParam(defaultValue = "5") int limit, @RequestParam(defaultValue = "0") int offset) {
        // Implement logic to retrieve and paginate suppliers
        PaginationPage<SupplierReadDto> suppliers = _supplierService.getAllSuppliers(limit, offset);

        List<SupplierReadDto> suppliersList = new ArrayList<>(suppliers.getRecords());
        System.out.println(suppliers.getRecords());
        SuccessResponseEntity<SupplierReadDto> successResponseEntity = new SuccessResponseEntity<>();
        successResponseEntity.setData(suppliersList);

        return ResponseEntity.ok(successResponseEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SupplierReadDto> getSupplierById(@PathVariable UUID id) {
        // Implement logic to retrieve a supplier by its ID
        SupplierReadDto supplierReadDto = _supplierService.getSupplierById(id);
        // Return the supplier as SupplierReadDto
        return ResponseEntity.ok(supplierReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID id) {
        // Implement logic to delete a supplier by its ID
        _supplierService.deleteSupplier(id);

        return ResponseEntity.noContent().build();
    }
}
