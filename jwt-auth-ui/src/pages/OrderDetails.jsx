import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import { getOrderById } from "../services/orderService";

function OrderDetails() {

    const { orderId } = useParams();

    const navigate = useNavigate();

    const [order, setOrder] = useState(null);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");

    useEffect(() => {

        loadOrder();

    }, []);

    const loadOrder = async () => {

        try {

            const response = await getOrderById(orderId);

            setOrder(response);

        } catch (error) {

            console.error(error);

            setError("Unable to load order details.");

        } finally {

            setLoading(false);

        }

    };

    if (loading) {

        return (

            <div className="container mt-5 text-center">

                <div className="spinner-border text-primary"></div>

                <p className="mt-3">
                    Loading Order Details...
                </p>

            </div>

        );

    }

    if (error) {

        return (

            <div className="container mt-5">

                <div className="alert alert-danger">

                    {error}

                </div>

            </div>

        );

    }

    if (!order) {

        return (

            <div className="container mt-5">

                <div className="alert alert-warning">

                    Order not found.

                </div>

            </div>

        );

    }

    return (

        <div className="container mt-4">

            <button
                className="btn btn-secondary mb-3"
                onClick={() => navigate("/dashboard/my-orders")}
            >
                ← Back
            </button>

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3 className="mb-0">

                        📦 Order Details

                    </h3>

                </div>

                <div className="card-body">

                    {/* Order Summary */}

                    <div className="row">

                        <div className="col-md-6">

                            <h5>Order Information</h5>

                            <hr />

                            <p>

                                <strong>Order ID :</strong>

                                {order.orderId}

                            </p>

                            <p>

                                <strong>Order Date :</strong>

                                {" "}

                                {new Date(order.orderDate).toLocaleString()}

                            </p>

                            <p>

                                <strong>Total Amount :</strong>

                                ₹{order.totalAmount}

                            </p>

                            <p>

                                <strong>Status :</strong>

                                {" "}

                                <span className="badge bg-success">

                                    {order.status}

                                </span>

                            </p>

                        </div>

                        <div className="col-md-6">

                            <h5>Customer Information</h5>

                            <hr />

                            <p>

                                <strong>Name :</strong>

                                {order.customerName}

                            </p>

                            <p>

                                <strong>Email :</strong>

                                {order.email}

                            </p>

                            <p>

                                <strong>Mobile :</strong>

                                {order.mobile}

                            </p>

                            <p>

                                <strong>Address :</strong>

                                {order.address}

                            </p>

                        </div>

                    </div>

                    <hr />

                    {/* Payment & Delivery */}

                    <div className="row">

                        <div className="col-md-6">

                            <h5>Payment</h5>

                            <hr />

                            <p>

                                <strong>Payment Status :</strong>

                                {" "}

                                <span className="badge bg-success">

                                    {order.paymentStatus}

                                </span>

                            </p>

                        </div>

                        <div className="col-md-6">

                            <h5>Delivery</h5>

                            <hr />

                            <p>

                                <strong>Delivery Status :</strong>

                                {" "}

                                <span className="badge bg-warning text-dark">

                                    {order.deliveryStatus}

                                </span>

                            </p>

                            {
                                order.trackingNumber && (

                                    <p>

                                        <strong>Tracking Number :</strong>

                                        {order.trackingNumber}

                                    </p>

                                )
                            }

                        </div>

                    </div>

                    <hr />

                    {/* Products */}

                    <h5>🛒 Ordered Products</h5>

                    <div className="table-responsive">

                        <table className="table table-bordered table-hover mt-3">

                            <thead className="table-dark">

                                <tr>

                                    <th>Product</th>

                                    <th>Category</th>

                                    <th>Description</th>

                                    <th>Quantity</th>

                                    <th>Price</th>

                                </tr>

                            </thead>

                            <tbody>

                                {
                                    order.items &&
                                    order.items.map((item, index) => (

                                        <tr key={index}>

                                            <td>

                                                {item.productName}

                                            </td>

                                            <td>

                                                {item.category}

                                            </td>

                                            <td>

                                                {item.description}

                                            </td>

                                            <td>

                                                {item.quantity}

                                            </td>

                                            <td>

                                                ₹{item.price}

                                            </td>

                                        </tr>

                                    ))
                                }

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default OrderDetails;