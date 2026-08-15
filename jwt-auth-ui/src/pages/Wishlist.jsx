import { useState } from "react";
import { FaHeart } from "react-icons/fa";

function Wishlist() {

    const [wishlist, setWishlist] = useState([

        {
            id: 1,
            productName: "Apple iPhone 16",
            price: 79999
        },

        {
            id: 2,
            productName: "Sony WH-1000XM6",
            price: 29999
        }

    ]);

    const removeItem = (id) => {

        setWishlist(

            wishlist.filter(item => item.id !== id)

        );

    };

    return (

        <div className="container mt-4">

            <h2 className="mb-4">

                ❤️ My Wishlist

            </h2>

            <div className="row">

                {

                    wishlist.length > 0 ?

                        wishlist.map(item => (

                            <div
                                className="col-md-4 mb-4"
                                key={item.id}
                            >

                                <div className="card shadow h-100">

                                    <div
                                        className="d-flex justify-content-center align-items-center"
                                        style={{
                                            height: "180px",
                                            background: "#f8f9fa"
                                        }}
                                    >

                                        <FaHeart
                                            size={70}
                                            color="red"
                                        />

                                    </div>

                                    <div className="card-body">

                                        <h5>{item.productName}</h5>

                                        <h4 className="text-success">

                                            ₹ {item.price.toLocaleString()}

                                        </h4>

                                    </div>

                                    <div className="card-footer d-flex justify-content-between">

                                        <button className="btn btn-primary">

                                            Add To Cart

                                        </button>

                                        <button
                                            className="btn btn-danger"
                                            onClick={() => removeItem(item.id)}
                                        >

                                            Remove

                                        </button>

                                    </div>

                                </div>

                            </div>

                        ))

                        :

                        <div className="alert alert-info">

                            Wishlist is empty.

                        </div>

                }

            </div>

        </div>

    );

}

export default Wishlist;