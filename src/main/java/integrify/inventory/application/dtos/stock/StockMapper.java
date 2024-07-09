package integrify.inventory.application.dtos.stock;

import integrify.inventory.domain.model.Stock;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface StockMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "supplierId", target = "supplier.id")
    Stock toStock(StockCreateDto stockCreateDto);

    @Mapping(source = "supplier.id", target = "supplierId")
    StockReadDto toStockReadDto(Stock stock);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "supplierId", target = "supplier.id")
    void updateStockFromDto(StockUpdateDto updateDto, @MappingTarget Stock stock);
}
