import Navbar from "../components/Navbar";
import { Outlet } from "react-router-dom";

function Dashboard() {

    return (

        <>

            <Navbar />

            <div className="container mt-4">

                <Outlet />

            </div>

        </>

    );

}

export default Dashboard;