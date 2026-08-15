import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { getUserId } from "../utils/tokenStorage";
import { getOrdersByCustomerId } from "../services/orderService";

function MyOrders() {
    const [orders, setOrders] = useState([]);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");

    useEffect(() => {
            loadOrders();

    }, []);

    const loadOrders = async () => {
        try {

            const customerId = getUserId();

            const response =
                await getOrdersByCustomerId(customerId);

            setOrders(response);

        }
        catch (error) {

            console.error(error);

            setError("Unable to load orders.");

        }
        finally {

            setLoading(false);

        }

    };

    if (loading) {

        return (

            <div className="container mt-5 text-center">

                <div className="spinner-border text-primary"></div>

                <p className="mt-3">
                    Loading Orders...
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

    return (

        <div className="container mt-4">

            <h2 className="mb-4">

                📦 My Orders

            </h2>

            {

                orders.length === 0 ?

                    (

                        <div className="alert alert-info">

                            No Orders Found.

                        </div>

                    )

                    :

                    (

                        <div className="row">

                            {

                                orders.map(order => (

                                    <div
                                        className="col-md-6 mb-4"
                                        key={order.orderId}
                                    >

                                        <div className="card shadow-sm h-100">

                                            <div className="card-body">

                                                <h5>

                                                    Order #

                                                    {order.orderId}

                                                </h5>

                                                <hr />

                                                <p>

                                                    <strong>

                                                        Amount :

                                                    </strong>

                                                    ₹

                                                    {order.totalAmount}

                                                </p>

                                                <p>

                                                    <strong>

                                                        Status :

                                                    </strong>

                                                    {order.status}

                                                </p>

                                                <p>

                                                    <strong>

                                                        Date :

                                                    </strong>

                                                    {

                                                        new Date(

                                                            order.orderDate

                                                        ).toLocaleString()

                                                    }

                                                </p>

                                                <Link

                                                    to={`/dashboard/order-details/${order.orderId}`}

                                                    className="btn btn-primary"

                                                >

                                                    View Details

                                                </Link>

                                            </div>

                                        </div>

                                    </div>

                                ))

                            }

                        </div>

                    )

            }

        </div>

    );

}

export default MyOrders;