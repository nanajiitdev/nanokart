import { useEffect, useState } from "react";

function ProductForm({
    show,
    onClose,
    onSave,
    selectedProduct
}) {

    const [formData, setFormData] = useState({
        productName: "",
        category: "",
        price: "",
        quantity: "",
        description: ""
    });

    useEffect(() => {

        if (selectedProduct) {

            setFormData({
                productName: selectedProduct.productName,
                category: selectedProduct.category,
                price: selectedProduct.price,
                quantity: selectedProduct.quantity,
                description: selectedProduct.description
            });

        } else {

            setFormData({
                productName: "",
                category: "",
                price: "",
                quantity: "",
                description: ""
            });

        }

    }, [selectedProduct]);

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = (e) => {

        e.preventDefault();

        onSave(formData);

    };

    if (!show) return null;

    return (

        <>
            <div className="modal fade show d-block">
                <div className="modal-dialog">
                    <div className="modal-content">

                        <div className="modal-header">

                            <h5 className="modal-title">
                                {selectedProduct ? "Update Product" : "Add Product"}
                            </h5>

                            <button
                                className="btn-close"
                                onClick={onClose}
                            ></button>

                        </div>

                        <form onSubmit={handleSubmit}>

                            <div className="modal-body">

                                <div className="mb-3">

                                    <label>Product Name</label>

                                    <input
                                        type="text"
                                        name="productName"
                                        className="form-control"
                                        value={formData.productName}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-3">

                                    <label>Category</label>

                                    <input
                                        type="text"
                                        name="category"
                                        className="form-control"
                                        value={formData.category}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-3">

                                    <label>Price</label>

                                    <input
                                        type="number"
                                        name="price"
                                        className="form-control"
                                        value={formData.price}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-3">

                                    <label>Quantity</label>

                                    <input
                                        type="number"
                                        name="quantity"
                                        className="form-control"
                                        value={formData.quantity}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-3">

                                    <label>Description</label>

                                    <textarea
                                        name="description"
                                        className="form-control"
                                        rows="3"
                                        value={formData.description}
                                        onChange={handleChange}
                                    />

                                </div>

                            </div>

                            <div className="modal-footer">

                                <button
                                    type="button"
                                    className="btn btn-secondary"
                                    onClick={onClose}
                                >
                                    Cancel
                                </button>

                                <button
                                    type="submit"
                                    className="btn btn-primary"
                                >
                                    {selectedProduct ? "Update" : "Save"}
                                </button>

                            </div>

                        </form>

                    </div>
                </div>
            </div>

            <div className="modal-backdrop fade show"></div>
        </>
    );

}

export default ProductForm;