package com.app.product.controller;

import com.app.common.web.dto.ApiResponse;
import com.app.common.web.dto.PageResponse;
import com.app.product.dto.ProductDto;
import com.app.product.service.ProductService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping({"", "/"})
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<PageResponse<ProductDto>> getAllProducts(
      @PageableDefault(size = 20) Pageable pageable) {
    return ResponseEntity.ok(productService.getAllProducts(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable UUID id) {
    return ResponseEntity.ok(ApiResponse.ok(productService.getProductById(id)));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<ProductDto>> createProduct(
      @Valid @RequestBody ProductDto productDto) {
    var created = productService.createProduct(productDto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri();
    return ResponseEntity.created(location).body(ApiResponse.ok(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductDto>> updateProduct(
      @PathVariable UUID id, @Valid @RequestBody ProductDto productDto) {
    return ResponseEntity.ok(ApiResponse.ok(productService.updateProduct(id, productDto)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
