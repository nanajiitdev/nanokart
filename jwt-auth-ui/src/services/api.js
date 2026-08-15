import axios from "axios";
import { getToken } from "../utils/tokenStorage";

const api = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json"
    }
});

api.interceptors.request.use((config) => {

    // Don't send JWT for login/register APIs
    if (
        config.url === "/auth/login" ||
        config.url === "/auth/register"
    ) {
        return config;
    }

    const token = getToken();

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;

});

export default api;