package com.ecommerce.product;

import com.common.boot.BaseSpringBootApplication;
import org.springframework.boot.SpringApplication;

@BaseSpringBootApplication(enableOpenApi = true, enableActuator = true, enableCaching = true)
public class ProductServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductServiceApplication.class, args);
  }
}
