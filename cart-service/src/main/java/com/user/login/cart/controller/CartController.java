package com.user.login.cart.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.login.cart.dto.AddToCartRequest;
import com.user.login.cart.dto.CartResponse;
import com.user.login.cart.dto.UpdateCartRequest;
import com.user.login.cart.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
@Validated
public class CartController {

    private final CartService cartService;


    private static final Logger log =
            LoggerFactory.getLogger(CartController.class);
    
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /*
     * Add Product To Cart
     */
    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(
            @Valid @RequestBody AddToCartRequest request) {
    	System.out.println("===========1=====request: "+request);
    	
    	log.info(
    		    "GET CART customerId={}",
    		    request.getProductId()
    		);
    	log.info(
    		    "ADD TO CART customerId={} productId={} quantity={}",
    		    request.getUserId(),
    		    request.getProductId(),
    		    request.getQuantity()
    		);

        CartResponse response = cartService.addToCart(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED);

    }

    /*
     * Get User Cart
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> getCart(
            @PathVariable Long userId) {
    	System.out.println("===========2=====request: "+userId);

        return ResponseEntity.ok(

                cartService.getCart(userId)

        );

    }

    /*
     * Update Quantity
     */
    @PutMapping("/{cartId}")
    public ResponseEntity<CartResponse> updateQuantity(

            @PathVariable Long cartId,

            @Valid @RequestBody UpdateCartRequest request) {
    	
    	System.out.println("==========3======cartId: "+cartId);

        return ResponseEntity.ok(

                cartService.updateQuantity(
                        cartId,
                        request)

        );

    }

    /*
     * Remove Product
     */
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> removeItem(

            @PathVariable Long cartId) {
    	System.out.println("===========4=====request: "+cartId);

        cartService.removeItem(cartId);

        return ResponseEntity.ok(

                "Product removed from cart successfully."

        );

    }

    /*
     * Clear Cart
     */
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(

            @PathVariable Long userId) {
     	System.out.println("===========5=====request: "+userId);

        cartService.clearCart(userId);

        return ResponseEntity.ok(

                "Cart cleared successfully."

        );

    }

    /*
     * Grand Total
     */
    @GetMapping("/total/{userId}")
    public ResponseEntity<BigDecimal> getGrandTotal(

            @PathVariable Long userId) {
    	System.out.println("===========6=====request: "+userId);
        return ResponseEntity.ok(

                cartService.calculateGrandTotal(userId)

        );

    }

}