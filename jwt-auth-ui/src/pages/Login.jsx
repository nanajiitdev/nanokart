import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import {
    FaEnvelope,
    FaLock,
    FaEye,
    FaEyeSlash
} from "react-icons/fa";

import { login } from "../services/authService";

import { saveLoginData } from "../utils/tokenStorage";

import "bootstrap/dist/css/bootstrap.min.css";

function Login() {

    const navigate = useNavigate();

    const [showPassword, setShowPassword] = useState(false);

    const [formData, setFormData] = useState({

        email: "",

        password: ""

    });

    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const response = await login(formData);

            saveLoginData(response);

         //   alert("Login Successful");

            navigate("/dashboard/home");

        } catch (error) {

            console.error(error);

            alert(

                error.response?.data?.message ||

                "Invalid Email or Password"

            );

        }

    };

    return (

        <div className="container vh-100 d-flex justify-content-center align-items-center">

            <div
                className="card shadow-lg p-4"
                style={{ width: "430px" }}
            >

                <div className="text-center mb-4">

                    <h2 className="fw-bold text-primary">

                        NanoKart

                    </h2>

                    <p className="text-muted">

                        Login to your account

                    </p>

                </div>

                <form onSubmit={handleSubmit}>

                    <div className="mb-3">

                        <label className="form-label">

                            Email

                        </label>

                        <div className="input-group">

                            <span className="input-group-text">

                                <FaEnvelope />

                            </span>

                            <input
                                type="email"
                                className="form-control"
                                name="email"
                                placeholder="Enter Email"
                                value={formData.email}
                                onChange={handleChange}
                                autoComplete="email"
                                required
                            />

                        </div>

                    </div>

                    <div className="mb-4">

                        <label className="form-label">

                            Password

                        </label>

                        <div className="input-group">

                            <span className="input-group-text">

                                <FaLock />

                            </span>

                            <input
                                type={
                                    showPassword
                                        ? "text"
                                        : "password"
                                }
                                className="form-control"
                                name="password"
                                placeholder="Enter Password"
                                value={formData.password}
                                onChange={handleChange}
                                autoComplete="current-password"
                                required
                            />

                            <button
                                type="button"
                                className="btn btn-outline-secondary"
                                onClick={() =>
                                    setShowPassword(
                                        !showPassword
                                    )
                                }
                            >

                                {

                                    showPassword

                                        ?

                                        <FaEyeSlash />

                                        :

                                        <FaEye />

                                }

                            </button>

                        </div>

                    </div>

                    <button
                        type="submit"
                        className="btn btn-primary w-100"
                    >

                        Login

                    </button>

                    <div className="text-center my-3">

                        <span className="text-muted">
                            OR
                        </span>

                    </div>

                    <a
                        href="http://localhost:8080/oauth2/authorization/google"
                        className="btn btn-outline-danger w-100"
                    >
                        <strong>G</strong>
                        {" "}
                        Continue with Google
                    </a>

                    <div className="text-center mt-4">

                        Don't have an account?

                        <Link
                            to="/register"
                            className="ms-2 fw-bold text-decoration-none"
                        >

                            Register

                        </Link>

                    </div>

                </form>

            </div>

        </div>

    );

}

export default Login;