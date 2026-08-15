import { useEffect, useState } from "react";

import ProductForm from "../components/ProductForm";

import {
    getProducts,
    saveProduct,
    updateProduct,
    deleteProduct
} from "../services/productService";

function AdminProducts() {

    const [products, setProducts] = useState([]);

    const [showModal, setShowModal] = useState(false);

    const [selectedProduct, setSelectedProduct] = useState(null);

    useEffect(() => {

        loadProducts();

    }, []);

    const loadProducts = async () => {

        try {

            const data = await getProducts();

            setProducts(data);

        } catch (error) {

            console.error(error);

            alert("Unable to load products.");

        }

    };

    const handleAdd = () => {

        setSelectedProduct(null);

        setShowModal(true);

    };

    const handleEdit = (product) => {

        setSelectedProduct(product);

        setShowModal(true);

    };

    const handleSave = async (formData) => {

        try {

            if (selectedProduct) {

                await updateProduct(selectedProduct.id, formData);

                alert("Product Updated Successfully");

            } else {

                await saveProduct(formData);

                alert("Product Added Successfully");

            }

            setShowModal(false);

            setSelectedProduct(null);

            loadProducts();

        } catch (error) {

            console.error(error);

            alert("Operation Failed");

        }

    };

    const handleDelete = async (id) => {

        if (!window.confirm("Are you sure you want to delete this product?")) {

            return;

        }

        try {

            await deleteProduct(id);

            alert("Product Deleted Successfully");

            loadProducts();

        } catch (error) {

            console.error(error);

            alert("Delete Failed");

        }

    };

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-primary text-white d-flex justify-content-between align-items-center">

                    <h3 className="mb-0">

                        Product Management

                    </h3>

                    <button
                        className="btn btn-light"
                        onClick={handleAdd}
                    >
                        + Add Product
                    </button>

                </div>

                <div className="card-body">

                    <table className="table table-hover table-bordered">

                        <thead className="table-dark">

                            <tr>

                                <th>ID</th>

                                <th>Product Name</th>

                                <th>Category</th>

                                <th>Price</th>

                                <th>Quantity</th>

                                <th>Description</th>

                                <th width="180">

                                    Action

                                </th>

                            </tr>

                        </thead>

                        <tbody>

                            {

                                products.length > 0 ?

                                    products.map(product => (

                                        <tr key={product.id}>

                                            <td>{product.id}</td>

                                            <td>{product.productName}</td>

                                            <td>{product.category}</td>

                                            <td>₹ {product.price}</td>

                                            <td>{product.quantity}</td>

                                            <td>{product.description}</td>

                                            <td>

                                                <button
                                                    className="btn btn-warning btn-sm me-2"
                                                    onClick={() => handleEdit(product)}
                                                >
                                                    Edit
                                                </button>

                                                <button
                                                    className="btn btn-danger btn-sm"
                                                    onClick={() => handleDelete(product.id)}
                                                >
                                                    Delete
                                                </button>

                                            </td>

                                        </tr>

                                    ))

                                    :

                                    <tr>

                                        <td
                                            colSpan="7"
                                            className="text-center"
                                        >

                                            No Products Available

                                        </td>

                                    </tr>

                            }

                        </tbody>

                    </table>

                </div>

            </div>

            <ProductForm

                show={showModal}

                selectedProduct={selectedProduct}

                onClose={() => {

                    setShowModal(false);

                    setSelectedProduct(null);

                }}

                onSave={handleSave}

            />

        </div>

    );

}

export default AdminProducts;