package com.app.product.model;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute implements Serializable {

  @Serial private static final long serialVersionUID = 1L;
  private String name;
  private String value;
  private String unit; // e.g., "kg", "pcs", "inch"
}
