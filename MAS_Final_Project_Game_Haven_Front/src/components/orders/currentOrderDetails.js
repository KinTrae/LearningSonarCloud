import React, { useState, useEffect } from 'react';
import { fetchCurrentOrder } from '../../services/orderService';
import OrderDetails from './orderDetails';

const CurrentOrderDetails = () => {
  const [orderData, setOrderData] = useState(null);
  const orderId = 1;

  useEffect(() => {
    const fetchOrderDetails = async () => {
      try {
        const data = await fetchCurrentOrder(orderId);
        setOrderData(data);
      } catch (error) {
        console.error('Error fetching orders:', error);
      }
    };
    fetchOrderDetails();
  }, [orderId]);

  return (
    <div>
      {orderData ? (
        <OrderDetails orderData={orderData} />
      ) : (
        <p>Loading order details...</p>
      )}
    </div>
  );
};

export default CurrentOrderDetails;
