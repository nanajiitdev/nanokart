import { useEffect, useState } from "react";

import {
    useNavigate,
    useSearchParams
} from "react-router-dom";

import {
    getProducts,
    searchProducts,
    getProductsByCategory
} from "../services/productService";

import { addToCart } from "../services/cartService";

import { getUserId } from "../utils/tokenStorage";

function Products() {

    const navigate = useNavigate();

    const [searchParams] = useSearchParams();

    const keyword = searchParams.get("keyword");

    const category = searchParams.get("category");

    const [products, setProducts] = useState([]);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");

    useEffect(() => {

        loadProducts();

    }, [keyword, category]);

    const loadProducts = async () => {

        try {

            setLoading(true);

            let data;

            if (keyword) {

                data = await searchProducts(keyword);

            }

            else if (category) {

                data = await getProductsByCategory(category);

            }

            else {

                data = await getProducts();

            }

            setProducts(data);

        }

        catch (error) {

            console.error(error);

            setError("Unable to load products.");

        }

        finally {

            setLoading(false);

        }

    };

    const handleViewDetails = (id) => {

        navigate(`/dashboard/product/${id}`);

    };

    const handleAddToCart = async (product) => {

        try {

            const request = {

                userId: Number(getUserId()),

                productId: product.id,

                productName: product.productName,

                productImage:
                    product.productImage ||
                    "https://placehold.co/400x400?text=NanoKart",

                unitPrice: product.price,

                quantity: 1

            };

            await addToCart(request);

            alert("Product added to cart successfully.");

            navigate("/dashboard/cart");

        }

        catch (error) {

            console.error(error);

            alert("Unable to add product to cart.");

        }

    };

    return (

        <div className="container mt-4">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>

                    Products

                </h2>

                {

                    keyword &&

                    <span className="badge bg-primary">

                        Search :
                        {" "}
                        {keyword}

                    </span>

                }

                {

                    category &&

                    <span className="badge bg-success">

                        Category :
                        {" "}
                        {category}

                    </span>

                }

            </div>

            {

                loading &&

                <div className="text-center">

                    <div className="spinner-border text-primary"></div>

                </div>

            }

            {

                error &&

                <div className="alert alert-danger">

                    {error}

                </div>

            }

            {

                !loading && products.length === 0 &&

                <div className="alert alert-warning">

                    No Products Found

                </div>

            }

            <div className="row">

                {

                    products.map(product => (

                        <div
                            className="col-lg-3 col-md-4 col-sm-6 mb-4"
                            key={product.id}
                        >

                            <div className="card h-100 shadow-sm">

                                <img
                                    src={
                                        product.productImage ||

                                        "https://placehold.co/400x300?text=NanoKart"
                                    }
                                    className="card-img-top"
                                    alt={product.productName}
                                    style={{
                                        height: "220px",
                                        objectFit: "cover"
                                    }}
                                />

                                <div className="card-body d-flex flex-column">

                                    <h5>

                                        {product.productName}

                                    </h5>

                                    <span className="badge bg-info mb-2">

                                        {product.category}

                                    </span>

                                    <p className="text-muted flex-grow-1">

                                        {product.description}

                                    </p>

                                    <h4 className="text-success">

                                        ₹ {product.price}

                                    </h4>

                                    <p>

                                        {

                                            product.quantity > 0 ?

                                                <span className="badge bg-success">

                                                    In Stock

                                                </span>

                                                :

                                                <span className="badge bg-danger">

                                                    Out of Stock

                                                </span>

                                        }

                                    </p>

                                    <div className="d-grid gap-2">

                                        <button
                                            className="btn btn-outline-primary"
                                            onClick={() =>
                                                handleViewDetails(product.id)
                                            }
                                        >

                                            View Details

                                        </button>

                                        <button
                                            className="btn btn-primary"
                                            disabled={product.quantity <= 0}
                                            onClick={() =>
                                                handleAddToCart(product)
                                            }
                                        >

                                            Add To Cart

                                        </button>

                                    </div>

                                </div>

                            </div>

                        </div>

                    ))

                }

            </div>

        </div>

    );

}

export default Products;