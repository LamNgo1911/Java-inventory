package integrify.inventory.domain.repository;

import integrify.inventory.domain.model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStockRepo {
    Stock save(Stock stock);
    Optional<Stock> findById(Long id);
    List<Stock> findAll();
    void deleteById(Long id);
    Optional<Stock> findByProductId(UUID productId);
}
