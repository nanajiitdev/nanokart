package com.user.login.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.login.cart.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Get all cart items for a user
    List<Cart> findByUserId(Long userId);

    // Check if product already exists in user's cart
    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);

    // Delete all cart items of a user
    void deleteByUserId(Long userId);

}