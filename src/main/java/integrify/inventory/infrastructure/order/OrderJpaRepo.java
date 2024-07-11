package integrify.inventory.infrastructure.order;

import integrify.inventory.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderJpaRepo extends JpaRepository<Order, UUID> {
    @Query("SELECT o FROM Order o WHERE o.orderDate >= :startDate AND o.orderDate < :endDate")
    List<Order> findAllByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}