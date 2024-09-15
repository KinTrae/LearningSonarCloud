import React from 'react';
import { renderImage } from '../../utils/util';

const CurrentOrderGameDetails = ({ game, onQuantityChange, onDelete }) => {
  const handleQuantityChange = (e) => {
    onQuantityChange(game.id, parseInt(e.target.value));
  };

  return (
    <li key={game.id}>
      <strong>ID:</strong> {game.id} <br />
      <strong>Name:</strong> {game.name} <br />
      {renderImage(game.image, game.name)}<br />
      <strong>Price:</strong> {game.price} <br />
      <strong>Number of ordered games:</strong>
      <input
        type="number"
        value={game.numberOfOrderedGames}
        min="1"
        onChange={handleQuantityChange}
      />
      <strong>Total Price:</strong> {game.price * game.numberOfOrderedGames} <br />
      <button onClick={() => onDelete(game.id)}>Delete</button>
      <hr />
    </li>
  );
};

export default CurrentOrderGameDetails;
