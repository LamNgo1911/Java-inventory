package integrify.inventory.application.services.supplier;

import integrify.inventory.application.dtos.stock.StockCreateDto;
import integrify.inventory.application.dtos.stock.StockReadDto;
import integrify.inventory.application.dtos.supplier.SupplierCreateDto;
import integrify.inventory.application.dtos.supplier.SupplierMapper;
import integrify.inventory.application.dtos.supplier.SupplierReadDto;
import integrify.inventory.application.dtos.supplier.SupplierUpdateDto;
import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.model.Order;
import integrify.inventory.domain.model.Stock;
import integrify.inventory.domain.model.Supplier;
import integrify.inventory.domain.repository.IOrderRepo;
import integrify.inventory.domain.repository.IStockRepo;
import integrify.inventory.domain.repository.ISupplierRepo;
import integrify.inventory.presentation.errorHandlers.BadRequestException;
import integrify.inventory.presentation.errorHandlers.ResourceNotFound;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService implements ISupplierService{
    @Autowired
    private Validator validator;

    @Autowired
    private SupplierMapper _supplierMapper;

    @Autowired
    private ISupplierRepo _supplierRepo;

    @Autowired
    private IStockRepo _stockRepo;

    @Autowired
    private IOrderRepo _orderRepo;

    @Override
    public SupplierReadDto createSupplier(SupplierCreateDto supplierCreateDto) {

        Set<ConstraintViolation<SupplierCreateDto>> violations = validator.validate(supplierCreateDto);
        if (!violations.isEmpty()) {
            // Handle validation errors by throwing an exception
            throw new ValidationException("Validation failed: " + violations.toString());
        }

        Optional<Supplier> supplier = _supplierRepo.findByEmail(supplierCreateDto.getEmail());

        if(supplier.isPresent()){
            throw new BadRequestException("Supplier already exists with email: " + supplierCreateDto.getEmail());
        }

        Supplier newSupplier = _supplierMapper.toSupplier(supplierCreateDto);
        _supplierRepo.save(newSupplier);

        return _supplierMapper.toSupplierReadDto(newSupplier);
    }

    @Override
    public SupplierReadDto updateSupplier(UUID id, SupplierUpdateDto supplierDetails) {
        Set<ConstraintViolation<SupplierUpdateDto>> violations = validator.validate(supplierDetails);
        if (!violations.isEmpty()) {
            // Handle validation errors by throwing an exception
            throw new ValidationException("Validation failed: " + violations.toString());
        }

        Supplier supplier = _supplierRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Supplier not found with ID: " + id));

        _supplierMapper.updateSupplierFromDto(supplierDetails, supplier);
        _supplierRepo.save(supplier);

        return _supplierMapper.toSupplierReadDto(supplier);
    }

    @Override
    public PaginationPage<SupplierReadDto> getAllSuppliers(int limit, int offset) {
        OffsetPage pageable = new OffsetPage(limit, offset);

        Page<Supplier> suppliers = _supplierRepo.findAll(pageable);

        List<SupplierReadDto> supplierReadDto = suppliers.stream().map(supplier -> _supplierMapper.toSupplierReadDto(supplier)).collect(Collectors.toList());
        System.out.println();
        return new PaginationPage<SupplierReadDto>()
                .setLimit(suppliers.getSize())
                .setTotalRecords(suppliers.getTotalElements())
                .setOffset(offset)
                .setRecords(supplierReadDto);
    }

    @Override
    public SupplierReadDto getSupplierById(UUID id) {
        Supplier supplier = _supplierRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Supplier not found with ID: " + id));

        return _supplierMapper.toSupplierReadDto(supplier);
    }

    @Override
    public void deleteSupplier(UUID id) {
        Supplier supplier = _supplierRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Supplier not found with ID: " + id));

        List<Order> orders = _orderRepo.findAllBySupplierId(id);

        for (Order order: orders){
            _orderRepo.deleteById(order.getId());
        }
        _supplierRepo.deleteById(id);
    }
}
