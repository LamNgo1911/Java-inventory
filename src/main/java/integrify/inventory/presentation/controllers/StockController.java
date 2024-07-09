package integrify.inventory.presentation.controllers;


import integrify.inventory.application.dtos.stock.StockCreateDto;
import integrify.inventory.application.dtos.stock.StockReadDto;
import integrify.inventory.application.dtos.stock.StockUpdateDto;
import integrify.inventory.application.services.stock.IStockService;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.model.Stock;
import integrify.inventory.presentation.shared.SuccessResponseEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

    @Autowired
    private IStockService _stockService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StockReadDto> createStock(@RequestBody @Valid StockCreateDto stockCreateDto) {
        StockReadDto stockReadDto = _stockService.createStock(stockCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StockReadDto> updateStock(@PathVariable UUID id, @RequestBody @Valid StockUpdateDto stockUpdateDto) {
        StockReadDto stockReadDto = _stockService.updateStock(id, stockUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(stockReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> getAllStocks(@RequestParam(defaultValue = "5") int limit, @RequestParam(defaultValue = "0") int offset) {
        PaginationPage<StockReadDto> stocks = _stockService.getAllStocks(limit, offset);
        List<StockReadDto> stocksList = new ArrayList<>(stocks.getRecords());
        SuccessResponseEntity<StockReadDto> successResponseEntity = new SuccessResponseEntity<>();
        successResponseEntity.setData(stocksList);
        return ResponseEntity.ok(successResponseEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<StockReadDto> getStockById(@PathVariable UUID id) {
        StockReadDto stockReadDto = _stockService.getStockById(id);
        return ResponseEntity.ok(stockReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable UUID id) {
        _stockService.deleteStock(id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/product/{productId}")
    public ResponseEntity<StockReadDto> getStockByProductId(@PathVariable UUID productId) {
        StockReadDto stockReadDto = _stockService.getStockByProductId(productId);

        return ResponseEntity.ok(stockReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<SuccessResponseEntity<StockReadDto>> getAllStocksBySupplierId(
            @PathVariable UUID supplierId, @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "0") int offset) {

        PaginationPage<StockReadDto> stocks = _stockService.getStocksBySupplierId(supplierId, limit, offset);

        List<StockReadDto> stocksList = new ArrayList<>(stocks.getRecords());
        SuccessResponseEntity<StockReadDto> successResponseEntity = new SuccessResponseEntity<>();
        successResponseEntity.setData(stocksList);
        return ResponseEntity.ok(successResponseEntity);
    }

}
