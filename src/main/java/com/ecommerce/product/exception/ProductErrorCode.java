package com.ecommerce.product.exception;

import com.ecommerce.common.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProductErrorCode implements ErrorCode {
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "The requested product was not found");

  private final HttpStatus status;
  private final String message;

  ProductErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
}
