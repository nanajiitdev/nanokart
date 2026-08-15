import { useEffect, useState } from "react";

import {
    getCustomerProfile,
    saveCustomerProfile
} from "../services/customerProfileService";

function CustomerProfile() {

    const [profile, setProfile] = useState({

        consumerId: "",

        userId: "",

        name: "",

        email: "",

        mobile: "",

        houseNo: "",

        street: "",

        landmark: "",

        city: "",

        district: "",

        state: "",

        country: "",

        pincode: ""

    });

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadProfile();

    }, []);

    const loadProfile = async () => {

        try {

            const response = await getCustomerProfile();

            setProfile(response);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };

    const handleChange = (e) => {

        setProfile({

            ...profile,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await saveCustomerProfile({

                houseNo: profile.houseNo,

                street: profile.street,

                landmark: profile.landmark,

                city: profile.city,

                district: profile.district,

                state: profile.state,

                country: profile.country,

                pincode: profile.pincode

            });

            alert("Customer Details Saved Successfully");

            loadProfile();

        } catch (error) {

            console.error(error);

            alert("Unable to save customer details.");

        }

    };

    if (loading) {

        return (

            <div className="text-center mt-5">

                <h4>Loading Customer Details...</h4>

            </div>

        );

    }

    return (

        <div className="container">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3 className="mb-0">

                        📍 Customer Details

                    </h3>

                </div>

                <div className="card-body">

                    <form onSubmit={handleSubmit}>

                        <div className="row">

                            <div className="col-md-6 mb-3">

                                <label className="form-label">

                                    Consumer ID

                                </label>

                                <input
                                    className="form-control"
                                    value={profile.consumerId || ""}
                                    disabled
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label className="form-label">

                                    User ID

                                </label>

                                <input
                                    className="form-control"
                                    value={profile.userId || ""}
                                    disabled
                                />

                            </div>

                        </div>

                        <div className="row">

                            <div className="col-md-4 mb-3">

                                <label className="form-label">

                                    Name

                                </label>

                                <input
                                    className="form-control"
                                    value={profile.name || ""}
                                    disabled
                                />

                            </div>

                            <div className="col-md-4 mb-3">

                                <label className="form-label">

                                    Email

                                </label>

                                <input
                                    className="form-control"
                                    value={profile.email || ""}
                                    disabled
                                />

                            </div>

                            <div className="col-md-4 mb-3">

                                <label className="form-label">

                                    Mobile

                                </label>

                                <input
                                    className="form-control"
                                    value={profile.mobile || ""}
                                    disabled
                                />

                            </div>

                        </div>

                        <hr />

                        <h5 className="mb-3">

                            Address Information

                        </h5>

                        <div className="row">

                            <div className="col-md-4 mb-3">

                                <label className="form-label">

                                    House No

                                </label>

                                <input
                                    className="form-control"
                                    name="houseNo"
                                    value={profile.houseNo || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-8 mb-3">

                                <label className="form-label">

                                    Street

                                </label>

                                <input
                                    className="form-control"
                                    name="street"
                                    value={profile.street || ""}
                                    onChange={handleChange}
                                />

                            </div>

                        </div>

                        <div className="mb-3">

                            <label className="form-label">

                                Landmark

                            </label>

                            <input
                                className="form-control"
                                name="landmark"
                                value={profile.landmark || ""}
                                onChange={handleChange}
                            />

                        </div>

                        <div className="row">

                            <div className="col-md-4 mb-3">

                                <label className="form-label">

                                    City

                                </label>

                                <input
                                    className="form-control"
                                    name="city"
                                    value={profile.city || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-4 mb-3">

                                <label className="form-label">

                                    District

                                </label>

                                <input
                                    className="form-control"
                                    name="district"
                                    value={profile.district || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-4 mb-3">

                                <label className="form-label">

                                    State

                                </label>

                                <input
                                    className="form-control"
                                    name="state"
                                    value={profile.state || ""}
                                    onChange={handleChange}
                                />

                            </div>

                        </div>

                        <div className="row">

                            <div className="col-md-6 mb-3">

                                <label className="form-label">

                                    Country

                                </label>

                                <input
                                    className="form-control"
                                    name="country"
                                    value={profile.country || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label className="form-label">

                                    Pincode

                                </label>

                                <input
                                    className="form-control"
                                    name="pincode"
                                    value={profile.pincode || ""}
                                    onChange={handleChange}
                                />

                            </div>

                        </div>

                        <div className="text-end">

                            <button
                                className="btn btn-success"
                                type="submit"
                            >

                                Save Customer Details

                            </button>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default CustomerProfile;