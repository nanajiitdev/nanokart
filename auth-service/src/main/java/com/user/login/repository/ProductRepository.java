package com.user.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.login.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByProductNameContainingIgnoreCase(String keyword);

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByProductNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String productName,
            String category,
            String description);

}
