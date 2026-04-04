package com.ecommerce.product;

import com.ecommerce.common.boot.EcomBootApplication;
import org.springframework.boot.SpringApplication;

@EcomBootApplication(enableOpenApi = true, enableActuator = true, enableCaching = true)
public class ProductServiceApplication {

  static void main(String[] args) {
    SpringApplication.run(ProductServiceApplication.class, args);
  }
}
