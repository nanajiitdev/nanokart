import axios from "axios";
import { getToken } from "../utils/tokenStorage";

const API_BASE_URL = "http://localhost:8080/api/user";

// Get Logged-in User Profile
export const getProfile = async () => {

    try {

        const response = await axios.get(

            `${API_BASE_URL}/profile`,

            {
                headers: {
                    Authorization: `Bearer ${getToken()}`
                }
            }

        );

        return response.data;

    } catch (error) {

        console.error("Error fetching profile:", error);

        throw error;

    }

};

// Update Logged-in User Profile
export const updateProfile = async (profile) => {

    try {

        const response = await axios.put(

            `${API_BASE_URL}/profile`,

            profile,

            {
                headers: {
                    Authorization: `Bearer ${getToken()}`
                }
            }

        );

        return response.data;

    } catch (error) {

        console.error("Error updating profile:", error);

        throw error;

    }

};