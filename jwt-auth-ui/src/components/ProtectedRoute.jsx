import { Navigate } from "react-router-dom";
import { getToken, removeToken } from "../utils/tokenStorage";
import { isTokenExpired } from "../services/jwtUtils";

function ProtectedRoute({ children }) {

    const token = getToken();

    if (!token || isTokenExpired(token)) {

        removeToken();

        return <Navigate to="/" replace />;

    }

    return children;
}

export default ProtectedRoute;