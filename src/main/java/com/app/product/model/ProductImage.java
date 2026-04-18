package com.app.product.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage implements Serializable {

  @Serial private static final long serialVersionUID = 1L;
  private String thumbnail;
  private String primary;
  private List<String> gallery;
}
