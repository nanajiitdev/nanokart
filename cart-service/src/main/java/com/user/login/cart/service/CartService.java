package com.user.login.cart.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.login.cart.client.InventoryServiceClient;
import com.user.login.cart.dto.AddToCartRequest;
import com.user.login.cart.dto.CartResponse;
import com.user.login.cart.dto.InventoryResponse;
import com.user.login.cart.dto.UpdateCartRequest;
import com.user.login.cart.entity.Cart;
import com.user.login.cart.repository.CartRepository;

@Service
@Transactional
public class CartService {

    private final CartRepository repository;
    private final InventoryServiceClient inventoryServiceClient;

    public CartService(CartRepository repository,InventoryServiceClient inventoryServiceClient) {
        this.repository = repository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    /*
     * Add Product To Cart
     */
    public CartResponse addToCart(AddToCartRequest request) {

        Optional<Cart> existingCart = repository
                .findByUserIdAndProductId(
                        request.getUserId(),
                        request.getProductId());

        Cart cart;

        if (existingCart.isPresent()) {

            cart = existingCart.get();

            cart.setQuantity(
                    cart.getQuantity() + request.getQuantity());

        } else {

            cart = new Cart();

            cart.setUserId(request.getUserId());

            cart.setProductId(request.getProductId());

            cart.setProductName(request.getProductName());

            cart.setProductImage(request.getProductImage());

            cart.setUnitPrice(
                    BigDecimal.valueOf(request.getUnitPrice()));

            cart.setQuantity(request.getQuantity());

        }

        cart.setTotalPrice(

                cart.getUnitPrice().multiply(

                        BigDecimal.valueOf(
                                cart.getQuantity()))

        );

        repository.save(cart);

        return map(cart);

    }

    /*
     * Get User Cart
     */
    public List<CartResponse> getCart(Long userId) {

        return repository

                .findByUserId(userId)

                .stream()

                .map(this::map)

                .collect(Collectors.toList());

    }

    /*
     * Update Quantity
     */
    public CartResponse updateQuantity(
            Long cartId,
            UpdateCartRequest request) {

        Cart cart = repository.findById(cartId)
                .orElseThrow(() ->
                        new RuntimeException("Cart Item Not Found"));

        Integer requestedQuantity = request.getQuantity();

        InventoryResponse inventory =
                inventoryServiceClient
                        .getInventoryByProductId(
                                cart.getProductId());

        if (requestedQuantity >
                inventory.getAvailableQuantity()) {

            throw new RuntimeException(
                    "Insufficient stock. Available quantity: "
                    + inventory.getAvailableQuantity());
        }

        cart.setQuantity(requestedQuantity);

        cart.setTotalPrice(
                cart.getUnitPrice()
                        .multiply(
                                BigDecimal.valueOf(
                                        requestedQuantity)));

        Cart savedCart = repository.save(cart);

        return map(savedCart);
    }

    /*
     * Remove Item
     */
    public void removeItem(Long cartId) {

        repository.deleteById(cartId);

    }

    /*
     * Clear Cart
     */
    public void clearCart(Long userId) {

        repository.deleteByUserId(userId);

    }

    /*
     * Grand Total
     */
    public BigDecimal calculateGrandTotal(Long userId) {

        return repository.findByUserId(userId)

                .stream()

                .map(Cart::getTotalPrice)

                .reduce(

                        BigDecimal.ZERO,

                        BigDecimal::add

                );

    }

    /*
     * DTO Mapper
     */
    private CartResponse map(Cart cart) {

        return new CartResponse(

                cart.getId(),

                cart.getUserId(),

                cart.getProductId(),

                cart.getProductName(),

                cart.getProductImage(),

                cart.getUnitPrice(),

                cart.getQuantity(),

                cart.getTotalPrice()

        );

    }

}