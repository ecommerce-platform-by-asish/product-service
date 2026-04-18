package com.app.product;

import com.app.common.boot.BaseSpringBootApplication;
import org.springframework.boot.SpringApplication;

@BaseSpringBootApplication(enableOpenApi = true, enableActuator = true, enableCaching = true)
public class ProductApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }
}
