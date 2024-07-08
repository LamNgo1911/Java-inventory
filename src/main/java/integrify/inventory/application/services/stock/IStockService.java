package integrify.inventory.application.services.stock;

import integrify.inventory.application.dtos.stock.StockCreateDto;
import integrify.inventory.application.dtos.stock.StockReadDto;
import integrify.inventory.application.dtos.stock.StockUpdateDto;
import integrify.inventory.application.shared.PaginationPage;
import integrify.inventory.domain.model.Stock;
import integrify.inventory.domain.model.Supplier;

import java.util.List;
import java.util.UUID;

public interface IStockService {
    public StockReadDto createStock(StockCreateDto stockCreateDto);
    public StockReadDto updateStock(Long id, StockUpdateDto stockDetails);
    public PaginationPage<StockReadDto> getAllStocks(int limit, int offset);
    public StockReadDto getStockById(Long id);
    public PaginationPage<StockReadDto> getStocksBySupplier(Supplier supplier, int limit, int offset);
    public StockReadDto getStockByProductId(UUID productId);
    public void deleteStock(Long id);
}
