import api from "./api";

/*
 * Place Order
 */
export const placeOrder = async (orderRequest) => {

    const response = await api.post(
        "/orders",
        orderRequest
    );

    return response.data;

};

/*
 * Get Orders By Customer Id
 */
export const getOrdersByCustomerId = async (customerId) => {

    const response = await api.get(
        `/orders/customer/${customerId}/orders`
    );

    return response.data;

};

/*
 * Get Order Details
 */
export const getOrderById = async (orderId) => {

    const response = await api.get(
        `/orders/${orderId}/details`
    );

    return response.data;

};

/*
 * Get All Orders (Admin - Future Use)
 */
export const getAllOrders = async () => {

    const response = await api.get(
        "/orders"
    );

    return response.data;

};

/*
 * Update Order Status (Future)
 */
export const updateOrderStatus = async (
    orderId,
    status
) => {

    const response = await api.put(
        `/orders/${orderId}/status`,
        {
            status
        }
    );

    return response.data;

};
export const createOrder = async (orderRequest) => {

    const response = await api.post(
        "/orders",
        orderRequest
    );

    return response.data;

};