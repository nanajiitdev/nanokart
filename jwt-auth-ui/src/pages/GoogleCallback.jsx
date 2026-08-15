import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";

import { saveLoginData } from "../utils/tokenStorage";

function GoogleCallback() {

    const navigate = useNavigate();

    const [searchParams] = useSearchParams();

    useEffect(() => {

        const token = searchParams.get("token");
        const userId = searchParams.get("userId");
        const name = searchParams.get("name");
        const email = searchParams.get("email");
        const role = searchParams.get("role");

        if (!token || !userId || !email) {

            console.error("Google login failed");

            navigate("/login");

            return;
        }

        // Same structure used by normal login
        saveLoginData({
            token,
            userId,
            name,
            email,
            role
        });

        console.log("Google login successful");

        navigate("/dashboard/home");

    }, [navigate, searchParams]);

    return (

        <div className="container vh-100 d-flex justify-content-center align-items-center">

            <div className="text-center">

                <div className="spinner-border text-primary"></div>

                <h5 className="mt-3">
                    Completing Google Login...
                </h5>

            </div>

        </div>

    );
}

export default GoogleCallback;