package com.ecommerce.product.exception;

import com.ecommerce.common.exception.BaseException;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException {

  public ProductNotFoundException(UUID id) {
    super("Product not found with id: " + id, ProductErrorCode.PRODUCT_NOT_FOUND);
  }

  public ProductNotFoundException(UUID id, Throwable cause) {
    super("Product not found with id: " + id, ProductErrorCode.PRODUCT_NOT_FOUND, cause);
  }
}
