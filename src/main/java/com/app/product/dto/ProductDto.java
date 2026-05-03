package com.app.product.dto;

import com.app.product.model.ProductAttribute;
import com.app.product.model.ProductImage;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductDto(
    UUID id,
    @NotBlank(message = "Product name is required") String name,
    String description,
    @NotNull(message = "Base price is required") @Positive(message = "Base price must be positive")
        BigDecimal basePrice,
    UUID categoryId,
    String brand,
    ProductImage imageUrls,
    List<ProductAttribute> attributes)
    implements Serializable {}
