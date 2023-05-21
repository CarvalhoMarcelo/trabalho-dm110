package br.inatel.dm110.product.interfaces.mappers;

import br.inatel.dm110.api.product.ProductTO;
import br.inatel.dm110.product.entities.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductTO productToProductDto(Product product);

    Product productDtoToProduct(ProductTO productTO);
}
