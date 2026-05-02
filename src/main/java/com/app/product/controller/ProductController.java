package com.app.product.controller;

import com.app.common.dto.PageResponse;
import com.app.product.dto.ProductDto;
import com.app.product.service.ProductService;
import com.app.security.annotation.SecurityRules;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping({"", "/"})
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @SecurityRules.PublicEndpoint
  @GetMapping({"", "/"})
  public PageResponse<ProductDto> getAllProducts(
      @ParameterObject @PageableDefault Pageable pageable) {
    log.info("Fetching products with pageable: {}", pageable);
    return productService.getAllProducts(pageable);
  }

  @GetMapping("/{id}")
  public ProductDto getProductById(@PathVariable UUID id) {
    return productService.getProductById(id);
  }

  @PostMapping
  public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
    var created = productService.createProduct(productDto);
    var location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.id())
            .toUri();
    return ResponseEntity.created(location).body(created);
  }

  @PutMapping("/{id}")
  public ProductDto updateProduct(
      @PathVariable UUID id, @Valid @RequestBody ProductDto productDto) {
    return productService.updateProduct(id, productDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
