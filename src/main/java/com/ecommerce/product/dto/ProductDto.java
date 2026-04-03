package com.ecommerce.product.dto;

import com.ecommerce.product.model.ProductAttribute;
import com.ecommerce.product.model.ProductImage;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

  private UUID id;

  @NotBlank(message = "Product name is required")
  private String name;

  private String description;

  @NotNull(message = "Base price is required")
  @Positive(message = "Base price must be positive")
  private BigDecimal basePrice;

  private UUID categoryId;

  private String brand;

  private ProductImage imageUrls;

  private List<ProductAttribute> attributes;
}
