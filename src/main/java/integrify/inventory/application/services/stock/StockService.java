package integrify.inventory.application.services.stock;

import integrify.inventory.application.dtos.stock.StockCreateDto;
import integrify.inventory.application.dtos.stock.StockMapper;
import integrify.inventory.application.dtos.stock.StockReadDto;
import integrify.inventory.application.dtos.stock.StockUpdateDto;
import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.model.Stock;
import integrify.inventory.domain.model.Supplier;
import integrify.inventory.domain.repository.IStockRepo;
import integrify.inventory.presentation.errorHandlers.ResourceNotFound;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class StockService implements IStockService {
    private Validator validator;

    @Autowired
    private StockMapper _stockMapper;

    @Autowired
    private IStockRepo _stockRepo;


    @Override
    public StockReadDto createStock(StockCreateDto stockCreateDto) {
        // Validate input data
        Set<ConstraintViolation<StockCreateDto>> violations = validator.validate(stockCreateDto);
        if (!violations.isEmpty()) {
            // Handle validation errors by throwing an exception
            throw new ValidationException("Validation failed: " + violations.toString());
        }

        Stock stock = _stockMapper.toStock(stockCreateDto);

        return _stockMapper.toStockReadDto(stock);
    }

    @Override
    public StockReadDto updateStock(Long id, StockUpdateDto stockDetails) {
        Set<ConstraintViolation<StockUpdateDto>> violations = validator.validate(stockDetails);
        if (!violations.isEmpty()) {
            // Handle validation errors by throwing an exception
            throw new ValidationException("Validation failed: " + violations.toString());
        }

        Stock stock = _stockRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Stock not found with ID: " + id));

        _stockMapper.updateStockFromDto(stockDetails, stock);

        // Save the updated stock entity back to the repository
        _stockRepo.save(stock);

        return _stockMapper.toStockReadDto(stock);
    }

    @Override
    public PaginationPage<StockReadDto> getAllStocks(int limit, int offset) {
        OffsetPage pageable = new OffsetPage(limit, offset);

        Page<Stock> stocks = _stockRepo.findAll(pageable);

        List<StockReadDto> stockReadDtos = stocks.stream().map(stock -> _stockMapper.toStockReadDto(stock)).collect(Collectors.toList());

        return new PaginationPage<StockReadDto>()
                .setLimit(stocks.getSize())
                .setTotalRecords(stocks.getTotalElements())
                .setOffset(offset)
                .setRecords(stockReadDtos);
    }

    @Override
    public StockReadDto getStockById(Long id) {
        Stock stock = _stockRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Stock not found with ID: " + id));


        return _stockMapper.toStockReadDto(stock);
    }

    @Override
    public PaginationPage<StockReadDto> getStocksBySupplier(Supplier supplier, int limit, int offset) {
        OffsetPage pageable = new OffsetPage(limit, offset);

        Page<Stock> stocks = _stockRepo.findAllStockBySupplierId(supplier.getId(), pageable)
                .orElseThrow(() -> new ResourceNotFound("Stocks not found with supplier ID: " + supplier.getId()));


        List<StockReadDto> stockReadDtos = stocks.stream().map(stock -> _stockMapper.toStockReadDto(stock)).collect(Collectors.toList());

        return new PaginationPage<StockReadDto>()
                .setLimit(stocks.getSize())
                .setTotalRecords(stocks.getTotalElements())
                .setOffset(offset)
                .setRecords(stockReadDtos);

    }

    @Override
    public StockReadDto getStockByProductId(UUID productId) {
        Stock stock = _stockRepo.findByProductId(productId).orElseThrow(() -> new ResourceNotFound("Stock not found with ID: " + productId));
        return _stockMapper.toStockReadDto(stock);
    }

    @Override
    public void deleteStock(Long id) {
        Stock stock = _stockRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Stock not found with ID: " + id));

        _stockRepo.deleteById(id);
    }
}
