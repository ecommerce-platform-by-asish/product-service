package com.app.product.controller;

import com.app.common.dto.ApiResponse;
import com.app.common.dto.PageResponse;
import com.app.product.dto.ProductDto;
import com.app.product.service.ProductService;
import com.app.security.annotation.PublicEndpoint;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping({"", "/"})
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PublicEndpoint
  @GetMapping({"", "/"})
  public ResponseEntity<PageResponse<ProductDto>> getAllProducts(
      @ParameterObject @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(productService.getAllProducts(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable UUID id) {
    return ApiResponse.ok(productService.getProductById(id)).toEntity();
  }

  @PostMapping
  public ResponseEntity<ApiResponse<ProductDto>> createProduct(
      @Valid @RequestBody ProductDto productDto) {
    var created = productService.createProduct(productDto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.id())
            .toUri();
    return ResponseEntity.created(location).body(ApiResponse.ok(created));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductDto>> updateProduct(
      @PathVariable UUID id, @Valid @RequestBody ProductDto productDto) {
    return ApiResponse.ok(productService.updateProduct(id, productDto)).toEntity();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
