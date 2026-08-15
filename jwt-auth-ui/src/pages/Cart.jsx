import { useEffect, useState } from "react";

import { useNavigate } from "react-router-dom";

import {

    getCart,

    updateQuantity,

    removeItem,

    clearCart,

    getGrandTotal

} from "../services/cartService";

import {

    getUserId

} from "../utils/tokenStorage";

function Cart() {

    const navigate = useNavigate();

    const userId = getUserId();

    const [cartItems, setCartItems] = useState([]);

    const [grandTotal, setGrandTotal] = useState(0);

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadCart();

    }, []);

    const loadCart = async () => {

        try {

            const items = await getCart(userId);

            setCartItems(items);

            const total = await getGrandTotal(userId);

            setGrandTotal(total);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };

    const increase = async (item) => {

    await updateQuantity(
        item.id,
        {
            quantity: item.quantity + 1
        }
    );

    await loadCart();
};

    const decrease = async (item) => {

    if (item.quantity === 1) {
        return;
    }

    await updateQuantity(
        item.id,
        {
            quantity: item.quantity - 1
        }
    );

    await loadCart();
};

    const deleteItem = async (cartId) => {

        if (!window.confirm("Remove this item?")) {

            return;

        }

        await removeItem(cartId);

        loadCart();

    };

    const handleClearCart = async () => {

        if (!window.confirm("Clear entire cart?")) {

            return;

        }

        await clearCart(userId);

        loadCart();

    };

    if (loading) {

        return (

            <div className="text-center mt-5">

                <div className="spinner-border text-primary"></div>

            </div>

        );

    }

    return (

        <div className="container mt-4">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>

                    My Shopping Cart

                </h2>

                {

                    cartItems.length > 0 &&

                    <button

                        className="btn btn-danger"

                        onClick={handleClearCart}

                    >

                        Clear Cart

                    </button>

                }

            </div>

            {

                cartItems.length === 0 ?

                (

                    <div className="card shadow">

                        <div className="card-body text-center">

                            <h3>

                                Your Cart is Empty

                            </h3>

                            <button

                                className="btn btn-primary mt-3"

                                onClick={() =>

                                    navigate("/dashboard/products")

                                }

                            >

                                Continue Shopping

                            </button>

                        </div>

                    </div>

                )

                :

                <>

                    {

                        cartItems.map(item => (

                            <div

                                className="card shadow mb-3"

                                key={item.id}

                            >

                                <div className="row g-0">

                                    <div className="col-md-3 text-center p-3">

                                        <img

                                            src={

                                                item.productImage ||

                                                "https://placehold.co/250x200"

                                            }

                                            className="img-fluid"

                                            alt={item.productName}

                                        />

                                    </div>

                                    <div className="col-md-9">

                                        <div className="card-body">

                                            <h4>

                                                {item.productName}

                                            </h4>

                                            <h5 className="text-success">

                                                ₹ {item.unitPrice}

                                            </h5>

                                            <div className="d-flex align-items-center mt-3">

                                                <button

                                                    className="btn btn-outline-secondary"

                                                    onClick={() =>

                                                        decrease(item)

                                                    }

                                                >

                                                    -

                                                </button>

                                                <input

                                                    readOnly

                                                    value={item.quantity}

                                                    className="form-control text-center mx-2"

                                                    style={{width:"70px"}}

                                                />

                                                <button

                                                    className="btn btn-outline-secondary"

                                                    onClick={() =>

                                                        increase(item)

                                                    }

                                                >

                                                    +

                                                </button>

                                            </div>

                                            <h5 className="mt-3">

                                                Total :

                                                <span className="text-primary">

                                                    {" "}₹ {item.totalPrice}

                                                </span>

                                            </h5>

                                            <button

                                                className="btn btn-danger mt-3"

                                                onClick={() =>

                                                    deleteItem(item.id)

                                                }

                                            >

                                                Remove

                                            </button>

                                        </div>

                                    </div>

                                </div>

                            </div>

                        ))

                    }

                    <div className="card shadow">

                        <div className="card-body">

                            <div className="row">

                                <div className="col-md-6">

                                    <h3>

                                        Grand Total

                                    </h3>

                                </div>

                                <div className="col-md-6 text-end">

                                    <h2 className="text-success">

                                        ₹ {grandTotal}

                                    </h2>

                                </div>

                            </div>

                            <hr/>

                            <div className="text-end">

                                <button

                                    className="btn btn-success btn-lg"

                                    onClick={() =>

                                        navigate("/dashboard/checkout")

                                    }

                                >

                                    Proceed To Checkout

                                </button>

                            </div>

                        </div>

                    </div>

                </>

            }

        </div>

    );

}

export default Cart;