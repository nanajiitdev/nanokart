import { NavLink, useNavigate } from "react-router-dom";
import { useState } from "react";

import { removeToken } from "../utils/tokenStorage";

import { getSuggestions } from "../services/productService";

function Navbar() {

    const navigate = useNavigate();

    const [keyword, setKeyword] = useState("");

    const [suggestions, setSuggestions] = useState([]);

    const [showSuggestions, setShowSuggestions] = useState(false);

    /*
     * Logout
     */
    const logout = () => {

        removeToken();

        navigate("/login");

    };

    /*
     * Search
     */
    const handleSearch = (e) => {

        e.preventDefault();

        if (!keyword.trim()) {

            navigate("/dashboard/products");

            return;

        }

        setShowSuggestions(false);

        navigate(

            `/dashboard/products?keyword=${encodeURIComponent(keyword)}`

        );

    };

    /*
     * Auto Suggest
     */
    const handleKeywordChange = async (e) => {

        const value = e.target.value;

        setKeyword(value);

        if (value.trim().length < 2) {

            setSuggestions([]);

            setShowSuggestions(false);

            return;

        }

        try {

            const data = await getSuggestions(value);

            setSuggestions(data);

            setShowSuggestions(true);

        }

        catch (error) {

            console.error(error);

        }

    };

    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-primary shadow">

            <div className="container-fluid">

                {/* Logo */}

                <NavLink
                    className="navbar-brand fw-bold fs-3"
                    to="/dashboard/home"
                >

                    <i className="bi bi-cart3 me-2"></i>

                    N@noK@rt

                </NavLink>

                {/* Mobile Button */}

                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                >

                    <span className="navbar-toggler-icon"></span>

                </button>

                <div
                    className="collapse navbar-collapse"
                    id="navbarNav"
                >

                    {/* Search */}

                    <div
                        className="position-relative mx-auto"
                        style={{ width: "42%" }}
                    >

                        <form
                            className="d-flex"
                            onSubmit={handleSearch}
                        >

                            <input
                                type="search"
                                className="form-control"
                                placeholder="Search Products..."
                                value={keyword}
                                onChange={handleKeywordChange}
                            />

                            <button
                                className="btn btn-warning ms-2"
                                type="submit"
                            >

                                <i className="bi bi-search"></i>

                            </button>

                        </form>

                        {

                            showSuggestions &&

                            suggestions.length > 0 && (

                                <div
                                    className="list-group position-absolute w-100 shadow"
                                    style={{
                                        zIndex: 9999,
                                        top: "100%"
                                    }}
                                >

                                    {

                                        suggestions
                                            .slice(0, 5)
                                            .map(product => (

                                                <button

                                                    key={product.id}

                                                    type="button"

                                                    className="list-group-item list-group-item-action"

                                                    onClick={() => {

                                                        setKeyword(product.productName);

                                                        setShowSuggestions(false);

                                                        navigate(

                                                            `/dashboard/product/${product.id}`

                                                        );

                                                    }}

                                                >

                                                    {product.productName}

                                                </button>

                                            ))

                                    }

                                </div>

                            )

                        }

                    </div>

                    {/* Menu */}

                    <ul className="navbar-nav ms-auto align-items-center">

                        <li className="nav-item">

                            <NavLink
                                className="nav-link"
                                to="/dashboard/home"
                            >

                                Home

                            </NavLink>

                        </li>

                        <li className="nav-item">

                            <NavLink
                                className="nav-link"
                                to="/dashboard/products"
                            >

                                Products

                            </NavLink>

                        </li>

                        <li className="nav-item">

                            <NavLink
                                className="nav-link"
                                to="/dashboard/wishlist"
                            >

                                Wishlist

                            </NavLink>

                        </li>

                        <li className="nav-item">

                            <NavLink
                                className="nav-link"
                                to="/dashboard/cart"
                            >

                                <i className="bi bi-cart3 me-1"></i>

                                Cart

                            </NavLink>

                        </li>

                        {/* My Account */}

                        <li className="nav-item dropdown">

                            <a
                                href="#"
                                className="nav-link dropdown-toggle"
                                role="button"
                                data-bs-toggle="dropdown"
                            >

                                <i className="bi bi-person-circle me-1"></i>

                                My Account

                            </a>

                            <ul className="dropdown-menu dropdown-menu-end">

                                <li>

                                    <NavLink
                                        className="dropdown-item"
                                        to="/dashboard/profile"
                                    >

                                        My Profile

                                    </NavLink>

                                </li>

                                <li>

                                    <NavLink
                                        className="dropdown-item"
                                        to="/dashboard/customer-profile"
                                    >

                                        Customer Profile

                                    </NavLink>

                                </li>

                                <li>

                                    <NavLink
                                        className="dropdown-item"
                                        to="/dashboard/my-orders"
                                    >

                                        My Orders

                                    </NavLink>

                                </li>

                                <li>

                                    <NavLink
                                        className="dropdown-item"
                                        to="/dashboard/contact"
                                    >

                                        Contact Us

                                    </NavLink>

                                </li>

                                <li>

                                    <hr className="dropdown-divider" />

                                </li>

                                <li>

                                    <button
                                        className="dropdown-item text-danger"
                                        onClick={logout}
                                    >

                                        Logout

                                    </button>

                                </li>

                            </ul>

                        </li>

                    </ul>

                </div>

            </div>

        </nav>

    );

}

export default Navbar;