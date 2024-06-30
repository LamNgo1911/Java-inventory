package integrify.inventory.infrastructure.stock;

import integrify.inventory.domain.repositories.stock.IStockRepo;
import integrify.inventory.domain.repositories.stock.Stock;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Stock> findAll() {
        return _stockJpaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        _stockJpaRepo.deleteById(id);
    }

    @Override
    public Optional<Stock> findByProductId(UUID productId) {
        return _stockJpaRepo.findByProductId(productId);
    }
}
