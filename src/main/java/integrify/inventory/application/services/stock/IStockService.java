package integrify.inventory.application.services.stock;

import integrify.inventory.domain.model.Stock;
import integrify.inventory.domain.model.Supplier;

import java.util.List;

public interface IStockService {
    public Stock createStock(Stock stock);
    public Stock updateStock(Long id, Stock stockDetails);
    public List<Stock> getAllStocks();
    public Stock getStockById(Long id);
    public List<Stock> getStocksBySupplier(Supplier supplier);
    public List<Stock> getStocksByProductId(String productId);
    public void deleteStock(Long id);
}
