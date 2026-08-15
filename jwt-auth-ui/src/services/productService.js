import axios from "axios";

import { getToken } from "../utils/tokenStorage";

const API_BASE_URL = "http://localhost:8080/api/products";

/*
 * Common Request Configuration
 */
const getConfig = () => {

    return {

        headers: {

            Authorization: `Bearer ${getToken()}`,

            "Content-Type": "application/json"

        }

    };

};

/*
 * Get All Products
 */
export const getProducts = async () => {

    const response = await axios.get(

        API_BASE_URL,

        getConfig()

    );

    return response.data;

};

/*
 * Get Product By Id
 */
export const getProductById = async (id) => {

    const response = await axios.get(

        `${API_BASE_URL}/${id}`,

        getConfig()

    );

    return response.data;

};

/*
 * Save Product
 */
export const saveProduct = async (product) => {

    const response = await axios.post(

        API_BASE_URL,

        product,

        getConfig()

    );

    return response.data;

};

/*
 * Update Product
 */
export const updateProduct = async (id, product) => {

    const response = await axios.put(

        `${API_BASE_URL}/${id}`,

        product,

        getConfig()

    );

    return response.data;

};

/*
 * Delete Product
 */
export const deleteProduct = async (id) => {

    const response = await axios.delete(

        `${API_BASE_URL}/${id}`,

        getConfig()

    );

    return response.data;

};

/*
 * Search Products
 */
export const searchProducts = async (keyword) => {

    const response = await axios.get(

        `${API_BASE_URL}/search?keyword=${encodeURIComponent(keyword)}`,

        getConfig()

    );

    return response.data;

};

/*
 * Get Products By Category
 */
export const getProductsByCategory = async (category) => {

    const response = await axios.get(

        `${API_BASE_URL}/category/${encodeURIComponent(category)}`,

        getConfig()

    );

    return response.data;

};
/*
 * Product Suggestions
 */
export const getSuggestions = async (keyword) => {

    const response = await axios.get(

        `${API_BASE_URL}/suggest?keyword=${encodeURIComponent(keyword)}`,

        getConfig()

    );

    return response.data;

};