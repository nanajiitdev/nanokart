import { useEffect, useState } from "react";

import { useNavigate, useParams } from "react-router-dom";

import { getProductById } from "../services/productService";

import { addToCart } from "../services/cartService";

import {

    getUserId

} from "../utils/tokenStorage";

function ProductDetails() {

    const { id } = useParams();

    const navigate = useNavigate();

    const [loading, setLoading] = useState(true);

    const [product, setProduct] = useState(null);

    const [quantity, setQuantity] = useState(1);

    useEffect(() => {

        loadProduct();

    }, [id]);

    const loadProduct = async () => {

        try {

            const response = await getProductById(id);

            setProduct(response);

        } catch (error) {

            console.error(error);

            alert("Unable to load product.");

        } finally {

            setLoading(false);

        }

    };

    const increase = () => {

        if (quantity < product.quantity) {

            setQuantity(quantity + 1);

        }

    };

    const decrease = () => {

        if (quantity > 1) {

            setQuantity(quantity - 1);

        }

    };

    const handleAddToCart = async () => {

        try {

            const request = {

                userId: Number(getUserId()),

                productId: product.id,

                productName: product.productName,

                productImage:
                    product.productImage ||
                    "https://placehold.co/400x400?text=NanoKart",

                unitPrice: product.price,

                quantity: quantity

            };

            await addToCart(request);

            alert("Product added to cart successfully.");

            navigate("/dashboard/cart");

        } catch (error) {

            console.error(error);

            alert("Unable to add product to cart.");

        }

    };

    const handleBuyNow = async () => {

        await handleAddToCart();

        navigate("/dashboard/checkout");

    };

    if (loading) {

        return (

            <div className="text-center mt-5">

                <div className="spinner-border text-primary"></div>

            </div>

        );

    }

    if (!product) {

        return (

            <div className="alert alert-danger mt-5">

                Product Not Found

            </div>

        );

    }

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="row">

                    <div className="col-md-5 p-4 text-center">

                        <img

                            src={
                                product.productImage ||

                                "https://placehold.co/500x450?text=NanoKart"
                            }

                            alt={product.productName}

                            className="img-fluid"

                        />

                    </div>

                    <div className="col-md-7">

                        <div className="card-body">

                            <h2>

                                {product.productName}

                            </h2>

                            <hr />

                            <h3 className="text-success">

                                ₹ {product.price}

                            </h3>

                            <p>

                                <strong>

                                    Category :

                                </strong>

                                {" "}

                                {product.category}

                            </p>

                            <p>

                                <strong>

                                    Available Stock :

                                </strong>

                                {" "}

                                {product.quantity}

                            </p>

                            <p>

                                <strong>

                                    Description

                                </strong>

                            </p>

                            <p>

                                {product.description}

                            </p>

                            <hr />

                            <div className="d-flex align-items-center mb-4">

                                <strong>

                                    Quantity

                                </strong>

                                <button

                                    className="btn btn-outline-secondary ms-3"

                                    onClick={decrease}

                                >

                                    -

                                </button>

                                <input

                                    value={quantity}

                                    readOnly

                                    className="form-control text-center mx-2"

                                    style={{ width: "70px" }}

                                />

                                <button

                                    className="btn btn-outline-secondary"

                                    onClick={increase}

                                >

                                    +

                                </button>

                            </div>

                            <button

                                className="btn btn-warning me-3"

                                onClick={handleAddToCart}

                            >

                                🛒 Add To Cart

                            </button>

                            <button

                                className="btn btn-success"

                                onClick={handleBuyNow}

                            >

                                Buy Now

                            </button>

                            <button

                                className="btn btn-link ms-3"

                                onClick={() => navigate(-1)}

                            >

                                Back

                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default ProductDetails;