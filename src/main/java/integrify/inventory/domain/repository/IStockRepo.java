package integrify.inventory.domain.repository;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.model.Stock;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStockRepo {
    Stock save(Stock stock);
    Optional<Stock> findById(Long id);
    Page<Stock> findAll(OffsetPage pageable);
    void deleteById(Long id);
    Optional<Stock> findByProductId(UUID productId);
    Optional<Page<Stock>> findAllStockBySupplierId(UUID supplierId, OffsetPage pageable);
}
