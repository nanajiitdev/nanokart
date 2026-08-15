import { useLocation, useNavigate } from "react-router-dom";

function OrderSuccess() {

    const navigate = useNavigate();

    const { state } = useLocation();

    const order = state?.order;

    const paymentMethod = state?.paymentMethod;

    if (!order) {

        return (

            <div className="container mt-5 text-center">

                <div className="alert alert-danger">

                    Invalid Order.

                </div>

                <button
                    className="btn btn-primary"
                    onClick={() => navigate("/dashboard/home")}
                >

                    Back To Home

                </button>

            </div>

        );

    }

    return (

        <div className="container mt-5">

            <div className="card shadow">

                <div className="card-body text-center">

                    <div
                        className="text-success mb-3"
                        style={{ fontSize: "70px" }}
                    >

                        ✅

                    </div>

                    <h2 className="text-success">

                        Order Placed Successfully

                    </h2>

                    <p className="text-muted">

                        Thank you for shopping with NanoKart.

                    </p>

                    <hr />

                    <div className="row">

                        <div className="col-md-6 text-start">

                            <h5>

                                Order ID

                            </h5>

                            <p>

                                {order.orderId}

                            </p>

                        </div>

                        <div className="col-md-6 text-start">

                            <h5>

                                Order Status

                            </h5>

                            <p>

                                {order.status}

                            </p>

                        </div>

                    </div>

                    <div className="row">

                        <div className="col-md-6 text-start">

                            <h5>

                                Order Date

                            </h5>

                            <p>

                                {order.orderDate}

                            </p>

                        </div>

                        <div className="col-md-6 text-start">

                            <h5>

                                Payment Method

                            </h5>

                            <p>

                                {paymentMethod}

                            </p>

                        </div>

                    </div>

                    <div className="mt-3">

                        <h4>

                            Total Amount

                        </h4>

                        <h2 className="text-primary">

                            ₹ {Number(order.totalAmount).toLocaleString()}

                        </h2>

                    </div>

                    <hr />

                    <div className="d-flex justify-content-center gap-3">

                        <button
                            className="btn btn-primary"
                            onClick={() =>
                                navigate("/dashboard/my-orders")
                            }
                        >

                            My Orders

                        </button>

                        <button
                            className="btn btn-success"
                            onClick={() =>
                                navigate("/dashboard/products")
                            }
                        >

                            Continue Shopping

                        </button>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default OrderSuccess;