package integrify.inventory.application.dtos.stock;

import integrify.inventory.domain.model.Stock;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface StockMapper {
    @Mapping(target = "id", ignore = true)
    Stock toStock(StockCreateDto stockCreateDto);

    StockReadDto toStockReadDto(Stock stock);

    void updateStockFromDto(StockUpdateDto updateDto, @MappingTarget Stock stock);
}
