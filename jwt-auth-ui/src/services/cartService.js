import api from "./api";

/*
 * Add Product To Cart
 */
export const addToCart = async (cartRequest) => {

    const response = await api.post(
        "/cart/add",
        cartRequest
    );

    return response.data;

};

/*
 * Get User Cart
 */
export const getCart = async (userId) => {

    const response = await api.get(`/cart/${userId}`);

    return response.data;

};

/*
 * Get Grand Total
 */
export const getGrandTotal = async (userId) => {

    const response = await api.get(`/cart/total/${userId}`);

    return response.data;

};

/*
 * Update Quantity
 */
export const updateQuantity = async (cartId, request) => {

    const response = await api.put(`/cart/${cartId}`, request);

    return response.data;

};

/*
 * Remove Item
 */
export const removeCartItem = async (cartId) => {

    const response = await api.delete(`/cart/${cartId}`);

    return response.data;

};

/*
 * Clear Cart
 */
export const clearCart = async (userId) => {

    const response = await api.delete(`/cart/clear/${userId}`);

    return response.data;

};

export const removeItem = async (cartId) => {

    const response = await api.delete(`/cart/${cartId}`);

    return response.data;

};