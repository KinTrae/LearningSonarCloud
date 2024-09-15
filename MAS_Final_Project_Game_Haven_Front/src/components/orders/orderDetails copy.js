import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { renderImage, formatDate } from '../../utils/util';
import { cancelOrder, sendOrder } from '../../services/orderService';
import CurrentOrderGameDetails from './currentOrderGameDetails';

const OrderDetails = ({ orderData }) => {
  const [orderedGames, setOrderedGames] = useState(orderData);
  const navigate = useNavigate();

  useEffect(() => {
    setOrderedGames(orderData);
  }, [orderData]);

  const handleCancel = async () => {
    try {
      const updatedOrder = await cancelOrder(orderData.order.id);
      setOrderedGames(updatedOrder);
      console.log('Order canceled successfully!');
    } catch (error) {
      console.error('Error canceling order:', error);
    }
  };

  const handleSend = async () => {
    try {
      const updatedOrder = await sendOrder(orderData.order.id);
      setOrderedGames(updatedOrder);
      console.log('Order sent successfully!');
    } catch (error) {
      console.error('Error sending order:', error);
    }
  };

  const handleBackToOrders = () => {
    navigate('/orders');
  };

  const handleQuantityChange = (gameId, newQuantity) => {
    setOrderedGames((prevState) => {
      const updatedGames = prevState.orderedGames.map((game) =>
        game.id === gameId ? { ...game, numberOfOrderedGames: newQuantity } : game
      );
      const updatedOrder = {
        ...prevState,
        orderedGames: updatedGames,
        order: {
          ...prevState.order,
          sum: updatedGames.reduce((total, game) => total + game.price * game.numberOfOrderedGames, 0),
        },
      };
      return updatedOrder;
    });
  };

  const handleDeleteGame = (gameId) => {
    setOrderedGames((prevState) => {
      const updatedGames = prevState.orderedGames.filter((game) => game.id !== gameId);
      const updatedOrder = {
        ...prevState,
        orderedGames: updatedGames,
        order: {
          ...prevState.order,
          sum: updatedGames.reduce((total, game) => total + game.price * game.numberOfOrderedGames, 0),
        },
      };
      return updatedOrder;
    });
  };

  const order = orderedGames.order;
  const games = orderedGames.orderedGames;

  return (
    <div>
      <button onClick={handleBackToOrders}>Return to Orders List</button>
      <div className="order-details">
        <h2>Order Details</h2>
        {order ? (
          <div>
            <ul>
              <li><strong>ID:</strong> {order.id}</li>
              <li><strong>User ID:</strong> {order.userId}</li>
              <li><strong>Email:</strong> {order.userMail}</li>
              <li><strong>Created Date:</strong> {formatDate(order.orderDate)}</li>
              <li><strong>Send Date:</strong> {formatDate(order.sendDate)}</li>
              <li><strong>Cancel Date:</strong> {formatDate(order.cancelDate)}</li>
              <li><strong>Status:</strong> {order.orderStatus}</li>
              <li><strong>Sum:</strong> {order.sum}</li>
            </ul>
            <hr />
            <button onClick={handleCancel}>Cancel</button>
            <button onClick={handleSend}>Send</button>
          </div>
        ) : (
          <p>No order data available. Sorry. Try again later.</p>
        )}
      </div>
      <h1>Ordered Games:</h1>
      <div className="list order-details-games">
        <ul>
          {games && games.length > 0 ? (
            games.map((game) => (
              <CurrentOrderGameDetails
                key={game.id}
                game={game}
                onQuantityChange={handleQuantityChange}
                onDelete={handleDeleteGame}
              />
            ))
          ) : (
            <p>No ordered games available. Sorry.</p>
          )}
        </ul>
      </div>
    </div>
  );
};

export default OrderDetails;
