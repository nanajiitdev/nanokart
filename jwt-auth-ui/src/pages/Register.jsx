import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import {
    FaUser,
    FaEnvelope,
    FaPhone,
    FaLock,
    FaEye,
    FaEyeSlash
} from "react-icons/fa";
import { register } from "../services/authService";
import "bootstrap/dist/css/bootstrap.min.css";

function Register() {

    const navigate = useNavigate();

    const [showPassword, setShowPassword] = useState(false);

    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        mobile: "",
        password: "",
        confirmPassword: ""
    });

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        if (formData.password !== formData.confirmPassword) {

            alert("Passwords do not match");

            return;

        }

        try {

            const request = {

                name: formData.name,

                email: formData.email,

                mobile: formData.mobile,

                password: formData.password

            };

            await register(request);

            alert("Registration Successful");

            navigate("/");

        } catch (error) {

            alert(error.response?.data?.message || "Registration Failed");

        }

    };

    return (

        <div className="container vh-100 d-flex justify-content-center align-items-center bg-light">

            <div className="card shadow-lg p-4 rounded-4" style={{ width: "450px" }}>

                <h2 className="text-center text-primary mb-4">

                    Customer Registration

                </h2>

                <form onSubmit={handleSubmit}>

                    {/* Name */}

                    <div className="mb-3">

                        <label className="form-label">

                            Name

                        </label>

                        <div className="input-group">

                            <span className="input-group-text">

                                <FaUser />

                            </span>

                            <input
                                type="text"
                                className="form-control"
                                name="name"
                                placeholder="Enter Name"
                                value={formData.name}
                                onChange={handleChange}
                                required
                            />

                        </div>

                    </div>

                    {/* Email */}

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
                                required
                            />

                        </div>

                    </div>

                    {/* Mobile */}

                    <div className="mb-3">

                        <label className="form-label">

                            Mobile Number

                        </label>

                        <div className="input-group">

                            <span className="input-group-text">

                                <FaPhone />

                            </span>

                            <input
                                type="tel"
                                className="form-control"
                                name="mobile"
                                placeholder="Enter Mobile Number"
                                value={formData.mobile}
                                onChange={handleChange}
                                maxLength="10"
                                pattern="[0-9]{10}"
                                required
                            />

                        </div>

                    </div>

                    {/* Password */}

                    <div className="mb-3">

                        <label className="form-label">

                            Password

                        </label>

                        <div className="input-group">

                            <span className="input-group-text">

                                <FaLock />

                            </span>

                            <input
                                type={showPassword ? "text" : "password"}
                                className="form-control"
                                name="password"
                                placeholder="Enter Password"
                                value={formData.password}
                                onChange={handleChange}
                                required
                            />

                            <button
                                type="button"
                                className="btn btn-outline-secondary"
                                onClick={() => setShowPassword(!showPassword)}
                            >

                                {showPassword ? <FaEyeSlash /> : <FaEye />}

                            </button>

                        </div>

                    </div>

                    {/* Confirm Password */}

                    <div className="mb-3">

                        <label className="form-label">

                            Confirm Password

                        </label>

                        <div className="input-group">

                            <span className="input-group-text">

                                <FaLock />

                            </span>

                            <input
                                type={showConfirmPassword ? "text" : "password"}
                                className="form-control"
                                name="confirmPassword"
                                placeholder="Confirm Password"
                                value={formData.confirmPassword}
                                onChange={handleChange}
                                required
                            />

                            <button
                                type="button"
                                className="btn btn-outline-secondary"
                                onClick={() =>
                                    setShowConfirmPassword(!showConfirmPassword)
                                }
                            >

                                {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}

                            </button>

                        </div>

                    </div>

                    <button
                        type="submit"
                        className="btn btn-success w-100"
                    >

                        Register

                    </button>

                    <div className="text-center mt-3">

                        Already have an account?

                        <Link
                            to="/"
                            className="text-decoration-none fw-bold ms-2"
                        >

                            Login

                        </Link>

                    </div>

                </form>

            </div>

        </div>

    );

}

export default Register;