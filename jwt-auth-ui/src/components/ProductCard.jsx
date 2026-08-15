import { useNavigate } from "react-router-dom";

import { addToCart } from "../services/cartService";

import { getUserId } from "../utils/tokenStorage";

function ProductCard({ product }) {

    const navigate = useNavigate();

    const handleAddToCart = async () => {

        try {

            const request = {

                userId: Number(getUserId()),

                productId: product.id,

                productName: product.productName,

                productImage:
                    product.productImage ||
                    "https://placehold.co/400x300?text=NanoKart",

                unitPrice: product.price,

                quantity: 1

            };

            await addToCart(request);

            alert("Product added to cart successfully.");

            navigate("/dashboard/cart");

        } catch (error) {

            console.error(error);

            alert("Unable to add product to cart.");

        }

    };

    const handleViewDetails = () => {

        navigate(`/dashboard/product/${product.id}`);

    };

    return (

        <div className="col-lg-3 col-md-4 col-sm-6 mb-4">

            <div className="card shadow h-100 border-0">

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

                    <h5 className="card-title">

                        {product.productName}

                    </h5>

                    <span className="badge bg-info mb-2">

                        {product.category}

                    </span>

                    <p className="card-text flex-grow-1">

                        {product.description}

                    </p>

                    <h4 className="text-success">

                        ₹ {product.price}

                    </h4>

                    <p>

                        Available :
                        <strong> {product.quantity}</strong>

                    </p>

                    <div className="d-grid gap-2">

                        <button
                            className="btn btn-outline-primary"
                            onClick={handleViewDetails}
                        >
                            View Details
                        </button>

                        <button
                            className="btn btn-primary"
                            onClick={handleAddToCart}
                            disabled={product.quantity <= 0}
                        >
                            🛒 Add To Cart
                        </button>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default ProductCard;