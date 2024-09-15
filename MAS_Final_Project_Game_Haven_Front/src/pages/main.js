import React from "react"
import { Link, Outlet   } from "react-router-dom";

const Main = () => {
    return(<div>
            <header>
                <h1>山 Game Haven </h1>
            </header>
            <nav>
                <Link to="/">Home</Link>    
                <Link to="/orders">Orders</Link>
                <Link to="/currentOrder">Current Order</Link>
                <Link to="/games">Store</Link>
            </nav>
            <section>
        <Outlet />
      </section>
        </div>);
}

export default Main;