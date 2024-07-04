package integrify.inventory.infrastructure.stock;

import integrify.inventory.domain.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockJpaRepo extends JpaRepository<Stock, Long> {
    public Optional<Stock> findByProductId(UUID productId);
}