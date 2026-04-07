package com.ecommerce.product.exception;

import com.common.exception.StatusCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ProductStatusCode implements StatusCode {
  PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "The requested product was not found");

  private final HttpStatusCode status;
  private final String message;

  ProductStatusCode(HttpStatusCode status, String message) {
    this.status = status;
    this.message = message;
  }
}
