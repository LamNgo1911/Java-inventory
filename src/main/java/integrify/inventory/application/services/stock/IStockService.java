package integrify.inventory.application.services.stock;

import integrify.inventory.application.dtos.stock.StockCreateDto;
import integrify.inventory.application.dtos.stock.StockReadDto;
import integrify.inventory.application.dtos.stock.StockUpdateDto;
import integrify.inventory.application.shared.PaginationPage;

import java.util.UUID;

public interface IStockService {
    public StockReadDto createStock(StockCreateDto stockCreateDto);
    public StockReadDto updateStock(UUID id, StockUpdateDto stockDetails);
    public PaginationPage<StockReadDto> getAllStocks(int limit, int offset);
    public StockReadDto getStockById(UUID id);
    public PaginationPage<StockReadDto> getStocksBySupplierId(UUID supplierId, int limit, int offset);
    public StockReadDto getStockByProductId(UUID productId);
    public void deleteStock(UUID id);
}
