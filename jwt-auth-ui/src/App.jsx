import { Routes, Route, Navigate } from "react-router-dom";

import { isAuthenticated } from "./utils/tokenStorage";

import Login from "./pages/Login";
import Register from "./pages/Register";

import Dashboard from "./pages/Dashboard";

import Home from "./pages/Home";
import Products from "./pages/Products";
import ProductDetails from "./pages/ProductDetails";
import Profile from "./pages/Profile";
import CustomerProfile from "./pages/CustomerProfile";
import ContactUs from "./pages/ContactUs";
import Cart from "./pages/Cart";
import Wishlist from "./pages/Wishlist";
import Checkout from "./pages/Checkout";
import OrderSuccess from "./pages/OrderSuccess";
import MyOrders from "./pages/MyOrders";
import OrderDetails from "./pages/OrderDetails";
import GoogleCallback from "./pages/GoogleCallback";

function PrivateRoute({ children }) {

    return isAuthenticated()

        ? children

        : <Navigate to="/login" replace />;

}

function App() {

    return (

        <Routes>

            {/* Default Route */}

            <Route
                path="/"
                element={<Navigate to="/login" replace />}
            />

            {/* Public Routes */}

            <Route
                path="/login"
                element={<Login />}
            />

            <Route
                path="/register"
                element={<Register />}
            />

            <Route
                path="/oauth2/callback"
                element={<GoogleCallback />}
            />

            {/* Protected Routes */}

            <Route
                path="/dashboard"
                element={
                    <PrivateRoute>
                        <Dashboard />
                    </PrivateRoute>
                }
            >

                {/* Default Dashboard */}

                <Route
                    index
                    element={<Navigate to="home" replace />}
                />

                {/* Home */}

                <Route
                    path="home"
                    element={<Home />}
                />

                {/* Products */}

                <Route
                    path="products"
                    element={<Products />}
                />

                {/* Product Details */}

                <Route
                    path="product/:id"
                    element={<ProductDetails />}
                />

                {/* Cart */}

                <Route
                    path="cart"
                    element={<Cart />}
                />

                {/* Checkout */}

                <Route
                    path="checkout"
                    element={<Checkout />}
                />

                {/* Order Success */}

                <Route
                    path="order-success"
                    element={<OrderSuccess />}
                />

                {/* My Orders */}

                <Route
                    path="my-orders"
                    element={<MyOrders />}
                />

                {/* Order Details */}

                <Route
                    path="order-details/:orderId"
                    element={<OrderDetails />}
                />

                {/* Wishlist */}

                <Route
                    path="wishlist"
                    element={<Wishlist />}
                />

                {/* User Profile */}

                <Route
                    path="profile"
                    element={<Profile />}
                />

                {/* Customer Profile */}

                <Route
                    path="customer-profile"
                    element={<CustomerProfile />}
                />

                {/* Contact */}

                <Route
                    path="contact"
                    element={<ContactUs />}
                />

            </Route>

            {/* Invalid Route */}

            <Route
                path="*"
                element={<Navigate to="/login" replace />}
            />

        </Routes>

    );

}

export default App;