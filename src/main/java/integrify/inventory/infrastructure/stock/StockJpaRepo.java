package integrify.inventory.infrastructure.stock;

import integrify.inventory.domain.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockJpaRepo extends JpaRepository<Stock, UUID> {
    public Optional<Stock> findByProductId(UUID productId);
    public  Optional<Page<Stock>> findAllStockBySupplierId(UUID SupplierId, Pageable pageable);
}