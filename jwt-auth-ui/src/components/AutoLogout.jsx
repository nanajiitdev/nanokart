import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { removeToken } from "../utils/tokenStorage";

function AutoLogout() {

    const navigate = useNavigate();

    useEffect(() => {

        let timer;

        const logout = () => {

            removeToken();

            alert("Session expired. Please login again.");

            navigate("/");

        };

        const resetTimer = () => {

            clearTimeout(timer);

            // 5 minutes
            timer = setTimeout(logout, 5 * 60 * 1000);

        };

        window.addEventListener("mousemove", resetTimer);
        window.addEventListener("keypress", resetTimer);
        window.addEventListener("click", resetTimer);
        window.addEventListener("scroll", resetTimer);

        resetTimer();

        return () => {

            clearTimeout(timer);

            window.removeEventListener("mousemove", resetTimer);
            window.removeEventListener("keypress", resetTimer);
            window.removeEventListener("click", resetTimer);
            window.removeEventListener("scroll", resetTimer);

        };

    }, [navigate]);

    return null;
}

export default AutoLogout;