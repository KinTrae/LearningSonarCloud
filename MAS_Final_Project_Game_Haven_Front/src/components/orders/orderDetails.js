import React, { useState, useEffect } from 'react';
import { sendOrder } from '../../services/orderService';
import CurrentOrderGameDetails from './currentOrderGameDetails';
import { deleteOrderedGameFromOrder } from '../../services/orderService';


const OrderDetails = ({ orderData }) => {
  const [orderedGames, setOrderedGames] = useState({
    orderedGames: [],
    order: { sum: 0 }
  });

  useEffect(() => {
    if (orderData) {
      setOrderedGames({
        orderedGames: orderData.orderedGames,
        order: { sum: orderData.order.sum }
      });
    } else {
      setOrderedGames({
        orderedGames: [],
        order: { sum: 0 }
      });
    }
  }, [orderData]);

  const handleSend = async () => {
    try {
      const updatedOrder = await sendOrder(orderData.order.id);

      console.log('Order sent successfully!');
    } catch (error) {
      console.error('Error sending order:', error);
    }
  };

  const deleteOrderedGame = async (gameId) => {
    try {
      const updatedOrder = await deleteOrderedGameFromOrder(orderData.order.id, gameId);
      setOrderedGames({
        orderedGames: updatedOrder.orderedGames,
        order: { sum: updatedOrder.order.sum }
      });
      console.log('Game deleted from order!');
    } catch (error) {
      console.error('Error deleting game from order:', error);
    }
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
    deleteOrderedGame(gameId);

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

  const games = orderedGames.orderedGames || [];
  const totalSum = orderedGames.order ? orderedGames.order.sum : 0;

  return (
    <div>
      {games.length > 0 ? (
        <div>
        <div className="order-details">
          <h2>Order Summary</h2>
          <p>Total Sum: {totalSum}</p>
          <button onClick={handleSend}>Send Order</button>
          </div>
          <h1>Ordered Games:</h1>

          <div className="list order-details-games">
            <ul>
              {games.map((game) => (
                <CurrentOrderGameDetails
                  key={game.id}
                  game={game}
                  onQuantityChange={handleQuantityChange}
                  onDelete={handleDeleteGame}
                />
              ))}
            </ul>
          </div>
        </div>
      ) : (
        <p>No ordered games available. Sorry.</p>
      )}
    </div>
  );
};

export default OrderDetails;
