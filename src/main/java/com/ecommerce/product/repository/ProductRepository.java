package com.ecommerce.product.repository;

import com.ecommerce.product.entity.Product;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
  Page<Product> findByIsActiveTrue(Pageable pageable);

  Optional<Product> findByIdAndIsActiveTrue(UUID id);
}
