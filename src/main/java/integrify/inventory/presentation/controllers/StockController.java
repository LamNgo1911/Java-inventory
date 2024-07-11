package integrify.inventory.presentation.controllers;

import integrify.inventory.application.dtos.stock.StockCreateDto;
import integrify.inventory.application.dtos.stock.StockReadDto;
import integrify.inventory.application.dtos.stock.StockUpdateDto;
import integrify.inventory.application.services.stock.IStockService;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.presentation.shared.SuccessResponseEntity;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private IStockService _stockService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StockReadDto> createStock(@RequestBody @Valid StockCreateDto stockCreateDto) {
        logger.info("Creating a new stock...");

        StockReadDto stockReadDto = _stockService.createStock(stockCreateDto);

        logger.info("New stock created with ID: {}", stockReadDto.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(stockReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StockReadDto> updateStock(@PathVariable UUID id, @RequestBody @Valid StockUpdateDto stockUpdateDto) {
        logger.info("Updating stock with ID: {}", id);

        StockReadDto stockReadDto = _stockService.updateStock(id, stockUpdateDto);

        logger.info("Stock with ID {} updated", id);

        return ResponseEntity.status(HttpStatus.OK).body(stockReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> getAllStocks(@RequestParam(defaultValue = "5") int limit, @RequestParam(defaultValue = "0") int offset) {
        logger.info("Retrieving all stocks...");

        PaginationPage<StockReadDto> stocks = _stockService.getAllStocks(limit, offset);

        List<StockReadDto> stocksList = new ArrayList<>(stocks.getRecords());

        logger.info("Retrieved {} stocks", stocksList.size());

        SuccessResponseEntity<StockReadDto> successResponseEntity = new SuccessResponseEntity<>();
        successResponseEntity.setData(stocksList);

        return ResponseEntity.ok(successResponseEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<StockReadDto> getStockById(@PathVariable UUID id) {
        logger.info("Retrieving stock with ID: {}", id);

        StockReadDto stockReadDto = _stockService.getStockById(id);

        logger.info("Retrieved stock with ID: {}", id);

        return ResponseEntity.ok(stockReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable UUID id) {
        logger.info("Deleting stock with ID: {}", id);

        _stockService.deleteStock(id);

        logger.info("Stock with ID {} deleted", id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/product/{productId}")
    public ResponseEntity<StockReadDto> getStockByProductId(@PathVariable UUID productId) {
        logger.info("Retrieving stock by product ID: {}", productId);

        StockReadDto stockReadDto = _stockService.getStockByProductId(productId);

        logger.info("Retrieved stock by product ID: {}", productId);

        return ResponseEntity.ok(stockReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> getAllStocksBySupplierId(
            @PathVariable UUID supplierId, @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "0") int offset) {

        logger.info("Retrieving all stocks by supplier ID: {}", supplierId);

        PaginationPage<StockReadDto> stocks = _stockService.getStocksBySupplierId(supplierId, limit, offset);

        List<StockReadDto> stocksList = new ArrayList<>(stocks.getRecords());

        logger.info("Retrieved {} stocks by supplier ID: {}", stocksList.size(), supplierId);

        SuccessResponseEntity<StockReadDto> successResponseEntity = new SuccessResponseEntity<>();
        successResponseEntity.setData(stocksList);

        return ResponseEntity.ok(successResponseEntity);
    }
}