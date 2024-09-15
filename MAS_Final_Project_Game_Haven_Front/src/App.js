import React from "react"
import {Route, Routes} from "react-router-dom"
import NotFound from "./pages/NotFound"
import Main from "./pages/main"
import HomeDemo from "./components/HomeDemo"

//games
import GameList from "./components/games/gamelist"
import GameDetails from "./components/games/gameDetails"

//orders
import Orders from "./components/orders/orderList"
import CurrentOrderDetails from "./components/orders/currentOrderDetails"


function App (){
        return(
                <div className="pageContent">
                <Routes>
                    <Route element={<Main />}>
                        <Route path="/" element={<HomeDemo/>}></Route> 
                        <Route path="/games" element={<GameList/>}></Route>
                        <Route path="/games/:id" element={<GameDetails/>}></Route>

                        <Route path="/orders" element={<Orders />}></Route>
                        <Route path="/currentOrder" element={<CurrentOrderDetails />} />

                        
                    </Route>
                    <Route path="*" element={<NotFound/>} />
                </Routes>
                </div>
        );
}

export default App;