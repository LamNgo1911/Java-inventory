package integrify.inventory.presentation.controllers;

import integrify.inventory.application.dtos.supplier.SupplierCreateDto;
import integrify.inventory.application.dtos.supplier.SupplierReadDto;
import integrify.inventory.application.dtos.supplier.SupplierUpdateDto;
import integrify.inventory.presentation.shared.SuccessResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SupplierReadDto> createSupplier(@RequestBody @Valid SupplierCreateDto supplierCreateDto) {
        // Implement logic to create a new supplier
        // Return the created supplier as SupplierReadDto
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SupplierReadDto> updateSupplier(@PathVariable Long id, @RequestBody @Valid SupplierUpdateDto supplierDetails) {
        // Implement logic to update an existing supplier
        // Return the updated supplier as SupplierReadDto
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<SuccessResponseEntity<SupplierReadDto>> getAllSuppliers(@RequestParam int limit, @RequestParam int offset) {
        // Implement logic to retrieve and paginate suppliers
        // Return a PaginationPage<SupplierReadDto> object
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SupplierReadDto> getSupplierById(@PathVariable Long id) {
        // Implement logic to retrieve a supplier by its ID
        // Return the supplier as SupplierReadDto
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        // Implement logic to delete a supplier by its ID
    }
}
