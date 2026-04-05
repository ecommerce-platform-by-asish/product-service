package com.ecommerce.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute {
  private String name;
  private String value;
  private String unit; // e.g., "kg", "pcs", "inch"
}
