package com.ecommerce.product.exception;

import com.common.exception.BaseException;
import java.io.Serial;
import java.util.UUID;

public class ProductNotFoundException extends BaseException {

  @Serial private static final long serialVersionUID = 1L;

  public ProductNotFoundException(UUID id) {
    super("Product not found with id: " + id, ProductStatusCode.PRODUCT_NOT_FOUND);
  }

  public ProductNotFoundException(UUID id, Throwable cause) {
    super("Product not found with id: " + id, ProductStatusCode.PRODUCT_NOT_FOUND, cause);
  }
}
