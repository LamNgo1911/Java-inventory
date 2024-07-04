package integrify.inventory.application.dtos.supplier;

import integrify.inventory.domain.model.Supplier;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface SupplierMapper {

    @Mapping(target = "id", ignore = true)
    Supplier toSupplier(SupplierCreateDto supplierCreateDto);

    SupplierReadDto toSupplierReadDto(Supplier supplier);

    void updateSupplierFromDto(SupplierUpdateDto updateDto, @MappingTarget Supplier supplier);
}
