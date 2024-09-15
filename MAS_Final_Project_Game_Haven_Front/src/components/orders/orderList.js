import React, { useState, useEffect } from "react";
import OrderListItem from "./orderListItem";
import { fetchUserOrders } from '../../services/orderService';

const Orders = () => {
  const [orders, setOrders] = useState([]);
  const userId = localStorage.getItem('userId');

  useEffect(() => {
    const fetchOrdersData = async () => {
      try {
        const data = await fetchUserOrders(userId);
        setOrders(data);
      } catch (error) {
        console.error('Error fetching orders:', error);
      }
    };

    fetchOrdersData();
  }, [userId]);

  return (
    <div className="order-list">
      <h1>Orders List</h1>
      <ul>
        {orders && orders.map((order) => (
          <OrderListItem key={order.id} order={order} />
        ))}
      </ul>
    </div>
  );
};

export default Orders;
