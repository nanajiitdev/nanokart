import { useEffect, useState } from "react";

import { FaUserCircle } from "react-icons/fa";

import {

    getProfile,

    updateProfile

} from "../services/profileService";

function Profile() {

    const [profile, setProfile] = useState({

        id: "",

        name: "",

        email: "",

        mobile: "",

        role: ""

    });

    const [loading, setLoading] = useState(true);

    useEffect(() => {

        loadProfile();

    }, []);

    const loadProfile = async () => {

        try {

            const response = await getProfile();

            setProfile(response);

        } catch (error) {

            console.error(error);

            alert("Unable to load profile.");

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

            await updateProfile({

                name: profile.name,

                mobile: profile.mobile

            });

            alert("Profile Updated Successfully");

            loadProfile();

        } catch (error) {

            console.error(error);

            alert("Unable to update profile.");

        }

    };

    if (loading) {

        return (

            <div className="text-center mt-5">

                <h4>Loading Profile...</h4>

            </div>

        );

    }

    return (

        <div className="container">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3 className="mb-0">

                        👤 My Profile

                    </h3>

                </div>

                <div className="card-body">

                    <div className="text-center mb-4">

                        <FaUserCircle
                            size={90}
                            color="#0d6efd"
                        />

                    </div>

                    <form onSubmit={handleSubmit}>

                        <div className="row">

                            <div className="col-md-6 mb-3">

                                <label className="form-label">

                                    User ID

                                </label>

                                <input
                                    type="text"
                                    className="form-control"
                                    value={profile.id}
                                    disabled
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label className="form-label">

                                    Role

                                </label>

                                <input
                                    type="text"
                                    className="form-control"
                                    value={profile.role}
                                    disabled
                                />

                            </div>

                        </div>

                        <div className="mb-3">

                            <label className="form-label">

                                Name

                            </label>

                            <input
                                type="text"
                                className="form-control"
                                name="name"
                                value={profile.name}
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
                                value={profile.email}
                                disabled
                            />

                        </div>

                        <div className="mb-3">

                            <label className="form-label">

                                Mobile Number

                            </label>

                            <input
                                type="text"
                                className="form-control"
                                name="mobile"
                                value={profile.mobile}
                                onChange={handleChange}
                                required
                            />

                        </div>

                        <div className="text-end">

                            <button
                                type="submit"
                                className="btn btn-success"
                            >

                                Save Changes

                            </button>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default Profile;