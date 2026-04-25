package com.app.product.service;

import com.app.common.dto.PageResponse;
import com.app.product.dto.ProductDto;
import com.app.product.exception.ProductNotFoundException;
import com.app.product.mapper.ProductMapper;
import com.app.product.repository.ProductRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Transactional(readOnly = true)
  public PageResponse<ProductDto> getAllProducts(Pageable pageable) {
    log.info("Fetching all products with pageable: {}", pageable);
    var page = productRepository.findByIsActiveTrue(pageable).map(productMapper::toDto);
    return PageResponse.of(page);
  }

  @Transactional(readOnly = true)
  @Cacheable(value = "product", key = "#id.toString()")
  public ProductDto getProductById(UUID id) {
    log.debug("Cache miss for product: {}", id);
    return productRepository
        .findByIdAndIsActiveTrue(id)
        .map(productMapper::toDto)
        .orElseThrow(() -> new ProductNotFoundException(id));
  }

  @Transactional
  @CachePut(value = "product", key = "#result.id.toString()")
  public ProductDto createProduct(ProductDto productDto) {
    var product = productMapper.toEntity(productDto);
    product.setIsActive(true); // Explicitly set it even though default is true
    var savedProduct = productRepository.save(product);
    return productMapper.toDto(savedProduct);
  }

  @Transactional
  @CachePut(value = "product", key = "#id.toString()")
  public ProductDto updateProduct(UUID id, ProductDto productDto) {
    var product =
        productRepository
            .findByIdAndIsActiveTrue(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

    productMapper.updateEntityFromDto(productDto, product);
    var updatedProduct = productRepository.save(product);

    return productMapper.toDto(updatedProduct);
  }

  @Transactional
  @CacheEvict(value = "product", key = "#id.toString()")
  public void deleteProduct(UUID id) {
    var product =
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

    product.setIsActive(false); // Soft delete
    productRepository.save(product);
  }
}
