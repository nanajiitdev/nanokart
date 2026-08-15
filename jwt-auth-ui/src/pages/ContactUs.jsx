import { useState } from "react";

function ContactUs() {

    const [form, setForm] = useState({
        name: "",
        email: "",
        subject: "",
        message: ""
    });

    const handleChange = (e) => {

        setForm({
            ...form,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = (e) => {

        e.preventDefault();

        alert("Thank you! Your message has been sent successfully.");

        setForm({
            name: "",
            email: "",
            subject: "",
            message: ""
        });

    };

    return (

        <div className="container my-5">

            <div className="text-center mb-5">

                <h2 className="fw-bold text-primary">

                    📞 Contact NanoKart

                </h2>

                <p className="text-muted">

                    We'd love to hear from you. Reach out for support,
                    feedback, or business enquiries.

                </p>

            </div>

            <div className="row g-4">

                {/* Contact Information */}

                <div className="col-lg-5">

                    <div className="card shadow h-100">

                        <div className="card-body">

                            <h4 className="mb-4">

                                Contact Information

                            </h4>

                            <p>

                                <i className="bi bi-geo-alt-fill text-danger me-2"></i>

                                <strong>Address</strong>

                            </p>

                            <p className="text-muted">

                                NanoKart Technologies

                                <br />

                                2-11, Neelampeta

                                <br />

                                Near YSR Colony

                                <br />

                                Narsipatnam

                                <br />

                                Anakapalli District

                                <br />

                                Andhra Pradesh - 531116

                                <br />

                                India

                            </p>

                            <hr />

                            <p>

                                <i className="bi bi-telephone-fill text-success me-2"></i>

                                <strong>Phone</strong>

                            </p>

                            <p className="text-muted">

                                +91 85200 04734

                            </p>

                            <hr />

                            <p>

                                <i className="bi bi-envelope-fill text-primary me-2"></i>

                                <strong>Email</strong>

                            </p>

                            <p className="text-muted">

                                support@nanokart.com

                            </p>

                            <hr />

                            <p>

                                <i className="bi bi-clock-fill text-warning me-2"></i>

                                <strong>Support Hours</strong>

                            </p>

                            <p className="text-muted">

                                Monday - Saturday

                                <br />

                                09:00 AM - 08:00 PM

                                <br /><br />

                                Sunday

                                <br />

                                10:00 AM - 05:00 PM

                            </p>

                        </div>

                    </div>

                </div>

                {/* Contact Form */}

                <div className="col-lg-7">

                    <div className="card shadow">

                        <div className="card-body">

                            <h4 className="mb-4">

                                Send us a Message

                            </h4>

                            <form onSubmit={handleSubmit}>

                                <div className="mb-3">

                                    <label className="form-label">

                                        Name

                                    </label>

                                    <input
                                        type="text"
                                        className="form-control"
                                        name="name"
                                        value={form.name}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-3">

                                    <label className="form-label">

                                        Email

                                    </label>

                                    <input
                                        type="email"
                                        className="form-control"
                                        name="email"
                                        value={form.email}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-3">

                                    <label className="form-label">

                                        Subject

                                    </label>

                                    <input
                                        type="text"
                                        className="form-control"
                                        name="subject"
                                        value={form.subject}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-4">

                                    <label className="form-label">

                                        Message

                                    </label>

                                    <textarea
                                        className="form-control"
                                        rows="5"
                                        name="message"
                                        value={form.message}
                                        onChange={handleChange}
                                        required
                                    ></textarea>

                                </div>

                                <button
                                    type="submit"
                                    className="btn btn-primary px-4"
                                >

                                    <i className="bi bi-send-fill me-2"></i>

                                    Send Message

                                </button>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

            {/* Help Section */}

            <div className="card shadow mt-5">

                <div className="card-body">

                    <h4 className="mb-4 text-center">

                        💬 We're Here to Help

                    </h4>

                    <div className="row text-center">

                        <div className="col-md-4 mb-3">

                            <i className="bi bi-box-seam display-5 text-primary"></i>

                            <h5 className="mt-3">

                                Orders

                            </h5>

                            <p className="text-muted">

                                Questions about orders,
                                cancellations,
                                returns and refunds.

                            </p>

                        </div>

                        <div className="col-md-4 mb-3">

                            <i className="bi bi-credit-card display-5 text-success"></i>

                            <h5 className="mt-3">

                                Payments

                            </h5>

                            <p className="text-muted">

                                Payment failures,
                                refunds,
                                invoices and billing.

                            </p>

                        </div>

                        <div className="col-md-4 mb-3">

                            <i className="bi bi-truck display-5 text-warning"></i>

                            <h5 className="mt-3">

                                Delivery

                            </h5>

                            <p className="text-muted">

                                Track orders,
                                shipping status
                                and delivery support.

                            </p>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default ContactUs;