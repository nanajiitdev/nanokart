const TOKEN_KEY = "jwtToken";

const USER_ID = "userId";

const USER_NAME = "userName";

const USER_EMAIL = "userEmail";

const USER_ROLE = "userRole";

/*
 * Save Complete Login Response
 */
export const saveLoginData = (response) => {

    localStorage.setItem(TOKEN_KEY, response.token);

    localStorage.setItem(USER_ID, response.userId);

    localStorage.setItem(USER_NAME, response.name);

    localStorage.setItem(USER_EMAIL, response.email);

    localStorage.setItem(USER_ROLE, response.role);

};

/*
 * Save Only Token
 */
export const saveToken = (token) => {

    localStorage.setItem(TOKEN_KEY, token);

};

/*
 * Get JWT Token
 */
export const getToken = () => {

    return localStorage.getItem(TOKEN_KEY);

};

/*
 * Get User Id
 */
export const getUserId = () => {

    return localStorage.getItem(USER_ID);

};

/*
 * Get User Name
 */
export const getUserName = () => {

    return localStorage.getItem(USER_NAME);

};

/*
 * Get User Email
 */
export const getUserEmail = () => {

    return localStorage.getItem(USER_EMAIL);

};

/*
 * Get User Role
 */
export const getUserRole = () => {

    return localStorage.getItem(USER_ROLE);

};

/*
 * Check Authentication
 */
export const isAuthenticated = () => {

    return !!localStorage.getItem(TOKEN_KEY);

};

/*
 * Logout User
 */
export const logout = () => {

    localStorage.removeItem(TOKEN_KEY);

    localStorage.removeItem(USER_ID);

    localStorage.removeItem(USER_NAME);

    localStorage.removeItem(USER_EMAIL);

    localStorage.removeItem(USER_ROLE);

};

/*
 * Backward Compatibility
 * (Existing code may still call removeToken())
 */
export const removeToken = () => {

    logout();

};