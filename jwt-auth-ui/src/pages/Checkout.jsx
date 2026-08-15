import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import {
    getCart,
    getGrandTotal,
    clearCart
} from "../services/cartService";

import { createOrder } from "../services/orderService";

import { getCustomerProfile } from "../services/customerProfileService";

import { getUserId } from "../utils/tokenStorage";

function Checkout() {

    const navigate = useNavigate();

    const userId = Number(getUserId());

    const [customer, setCustomer] = useState(null);

    const [cartItems, setCartItems] = useState([]);

    const [grandTotal, setGrandTotal] = useState(0);

    const [paymentMethod, setPaymentMethod] = useState("COD");

    const [loading, setLoading] = useState(true);

    const [placingOrder, setPlacingOrder] = useState(false);

    useEffect(() => {

        loadCheckoutData();

    }, []);

    const loadCheckoutData = async () => {

        try {

            const [

                customerResponse,

                cartResponse,

                totalResponse

            ] = await Promise.all([

                getCustomerProfile(),

                getCart(userId),

                getGrandTotal(userId)

            ]);

            setCustomer(customerResponse);

            setCartItems(cartResponse);

            setGrandTotal(totalResponse);

        } catch (error) {

            console.error(error);

            alert("Unable to load checkout details.");

        } finally {

            setLoading(false);

        }

    };

    const handlePlaceOrder = async () => {

        if (cartItems.length === 0) {

            alert("Your cart is empty.");

            return;

        }

        if (!customer?.houseNo) {

            alert("Please complete your delivery address.");

            navigate("/dashboard/customer-profile");

            return;

        }

        const orderRequest = {

            customerId: userId,

            items: cartItems.map(item => ({

                productId: item.productId,

                quantity: item.quantity

            }))

        };

        try {

            setPlacingOrder(true);

            const orderResponse = await createOrder(orderRequest);

            await clearCart(userId);

            alert("Order Placed Successfully");

            navigate("/order-success", {

                state: {

                    order: orderResponse,

                    paymentMethod: paymentMethod

                }

            });

        } catch (error) {

            console.error(error);

            alert("Failed to place order.");

        } finally {

            setPlacingOrder(false);

        }

    };

    if (loading) {

        return (

            <div className="container mt-5 text-center">

                <div className="spinner-border text-primary"></div>

                <h5 className="mt-3">

                    Loading Checkout...

                </h5>

            </div>

        );

    }
        return (

        <div className="container mt-4">

            <h2 className="mb-4">

                Checkout

            </h2>

            <div className="row">

                {/* Delivery Address */}

                <div className="col-lg-4">

                    <div className="card shadow mb-4">

                        <div className="card-header bg-primary text-white">

                            <h5 className="mb-0">

                                Delivery Address

                            </h5>

                        </div>

                        <div className="card-body">

                            <h5>{customer?.name}</h5>

                            <p className="mb-1">

                                {customer?.mobile}

                            </p>

                            <hr />

                            <p className="mb-1">

                                {customer?.houseNo}

                            </p>

                            <p className="mb-1">

                                {customer?.street}

                            </p>

                            <p className="mb-1">

                                {customer?.landmark}

                            </p>

                            <p className="mb-1">

                                {customer?.city}

                            </p>

                            <p className="mb-1">

                                {customer?.district}

                            </p>

                            <p className="mb-1">

                                {customer?.state}

                            </p>

                            <p className="mb-1">

                                {customer?.country}

                            </p>

                            <p>

                                {customer?.pincode}

                            </p>

                            <button
                                className="btn btn-outline-primary w-100"
                                onClick={() =>
                                    navigate("/dashboard/customer-profile")
                                }
                            >

                                Change Address

                            </button>

                        </div>

                    </div>

                </div>

                {/* Order Summary */}

                <div className="col-lg-8">

                    <div className="card shadow">

                        <div className="card-header bg-success text-white">

                            <h5 className="mb-0">

                                Order Summary

                            </h5>

                        </div>

                        <div className="card-body">

                            <table className="table table-bordered">

                                <thead>

                                    <tr>

                                        <th>Product</th>

                                        <th>Price</th>

                                        <th>Qty</th>

                                        <th>Total</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    {

                                        cartItems.map(item => (

                                            <tr key={item.id}>

                                                <td>

                                                    {item.productName}

                                                </td>

                                                <td>

                                                    ₹ {Number(item.unitPrice).toLocaleString()}

                                                </td>

                                                <td>

                                                    {item.quantity}

                                                </td>

                                                <td>

                                                    ₹ {Number(item.totalPrice).toLocaleString()}

                                                </td>

                                            </tr>

                                        ))

                                    }

                                </tbody>

                            </table>

                            <hr />

                            <div className="row">

                                <div className="col-md-6">

                                    <h5>

                                        Payment Method

                                    </h5>

                                    <div className="form-check">

                                        <input
                                            className="form-check-input"
                                            type="radio"
                                            checked={paymentMethod === "COD"}
                                            onChange={() =>
                                                setPaymentMethod("COD")
                                            }
                                        />

                                        <label className="form-check-label">

                                            Cash On Delivery

                                        </label>

                                    </div>

                                    <div className="form-check">

                                        <input
                                            className="form-check-input"
                                            type="radio"
                                            checked={paymentMethod === "UPI"}
                                            onChange={() =>
                                                setPaymentMethod("UPI")
                                            }
                                        />

                                        <label className="form-check-label">

                                            UPI

                                        </label>

                                    </div>

                                    <div className="form-check">

                                        <input
                                            className="form-check-input"
                                            type="radio"
                                            checked={paymentMethod === "CARD"}
                                            onChange={() =>
                                                setPaymentMethod("CARD")
                                            }
                                        />

                                        <label className="form-check-label">

                                            Credit / Debit Card

                                        </label>

                                    </div>

                                </div>

                                <div className="col-md-6 text-end">

                                    <h4>

                                        Grand Total

                                    </h4>

                                    <h2 className="text-success">

                                        ₹ {Number(grandTotal).toLocaleString()}

                                    </h2>

                                </div>

                            </div>

                            <hr />

                            <div className="text-end">

                                <button
                                    className="btn btn-success btn-lg"
                                    disabled={placingOrder}
                                    onClick={handlePlaceOrder}
                                >

                                    {

                                        placingOrder

                                            ?

                                            "Placing Order..."

                                            :

                                            "Place Order"

                                    }

                                </button>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default Checkout;