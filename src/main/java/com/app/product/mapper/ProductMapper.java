package com.app.product.mapper;

import com.app.common.mapper.BaseMapperConfig;
import com.app.product.dto.ProductDto;
import com.app.product.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = BaseMapperConfig.class)
public interface ProductMapper {

  ProductDto toDto(Product product);

  Product toEntity(ProductDto dto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntityFromDto(ProductDto dto, @MappingTarget Product entity);
}
