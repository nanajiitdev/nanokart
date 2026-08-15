import { useEffect, useState } from "react";

import {
    Link,
    useNavigate
} from "react-router-dom";

import CategoryCard from "../components/CategoryCard";
import ProductCard from "../components/ProductCard";

import { getProducts } from "../services/productService";

function Home() {

    const navigate = useNavigate();

    const [products, setProducts] = useState([]);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");

    const categories = [

        {
            title: "Mobiles",
            icon: "📱"
        },

        {
            title: "Laptops",
            icon: "💻"
        },

        {
            title: "Fashion",
            icon: "👕"
        },

        {
            title: "Electronics",
            icon: "🎧"
        },

        {
            title: "Home",
            icon: "🏠"
        },

        {
            title: "Watches",
            icon: "⌚"
        }

    ];

    useEffect(() => {

        loadProducts();

    }, []);

    const loadProducts = async () => {

        try {

            const data = await getProducts();

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

    return (

        <div className="container-fluid">

            {/* Hero Section */}

            <div className="bg-primary text-white rounded shadow p-5 text-center">

                <h1 className="display-4 fw-bold">

                    Welcome to NanoKart

                </h1>

                <p className="lead">

                    Shop Smart. Shop Fast. Shop with Confidence.

                </p>

                <Link
                    to="/dashboard/products"
                    className="btn btn-warning btn-lg mt-3"
                >

                    Shop Now

                </Link>

            </div>

            {/* Categories */}

            <div className="mt-5">

                <h2 className="mb-4">

                    Shop by Category

                </h2>

                <div className="row">

                    {

                        categories.map(category => (

                            <CategoryCard

                                key={category.title}

                                title={category.title}

                                icon={category.icon}

                                onClick={() =>

                                    navigate(

                                        `/dashboard/products?category=${encodeURIComponent(category.title)}`

                                    )

                                }

                            />

                        ))

                    }

                </div>

            </div>

            {/* Featured Products */}

            <div className="mt-5">

                <h2 className="mb-4">

                    Featured Products

                </h2>

                {

                    loading &&

                    <div className="text-center">

                        <h4>

                            Loading Products...

                        </h4>

                    </div>

                }

                {

                    error &&

                    <div className="alert alert-danger">

                        {error}

                    </div>

                }

                {

                    !loading && !error &&

                    <div className="row">

                        {

                            products.length > 0

                                ?

                                products.map(product => (

                                    <ProductCard

                                        key={product.id}

                                        product={product}

                                    />

                                ))

                                :

                                <div className="text-center">

                                    <h4>

                                        No Products Available

                                    </h4>

                                </div>

                        }

                    </div>

                }

            </div>

        </div>

    );

}

export default Home;