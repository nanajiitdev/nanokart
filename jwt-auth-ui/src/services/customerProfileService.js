import axios from "axios";
import { getToken } from "../utils/tokenStorage";

const API_URL = "http://localhost:8080/api/customer";

// Get Customer Profile
export const getCustomerProfile = async () => {

    const response = await axios.get(

        `${API_URL}/profile`,

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;

};

// Save Customer Profile
export const saveCustomerProfile = async (profile) => {

    const response = await axios.post(

        `${API_URL}/profile`,

        profile,

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;

};