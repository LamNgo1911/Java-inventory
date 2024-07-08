package integrify.inventory.infrastructure.stock;

import integrify.inventory.application.shared.OffsetPage;
import integrify.inventory.domain.repository.IStockRepo;
import integrify.inventory.domain.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StockRepo implements IStockRepo {
    @Autowired
    private StockJpaRepo _stockJpaRepo;

    @Override
    public Stock save(Stock stock) {
        return _stockJpaRepo.save(stock);
    }

    @Override
    public Optional<Stock> findById(Long id) {
        return _stockJpaRepo.findById(id);
    }

    @Override
    public Page<Stock> findAll(OffsetPage pageable) {
        return _stockJpaRepo.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        _stockJpaRepo.deleteById(id);
    }

    @Override
    public Optional<Stock> findByProductId(UUID productId) {
        return _stockJpaRepo.findByProductId(productId);
    }

    @Override
    public Optional<Page<Stock>> findAllStockBySupplierId(UUID supplierId, OffsetPage pageable) {
        return _stockJpaRepo.findAllStockBySupplierId(supplierId, pageable);
    }
}
