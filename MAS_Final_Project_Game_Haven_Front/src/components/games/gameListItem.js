import React from "react";
import { Link } from "react-router-dom";
import { renderImage } from "../../utils/util";

const GameListItem = ({ game, onBuyClick, userId }) => {

  return (
    <div> 
      <li key={game.id}>
        <Link to={`http://localhost:3000/games/${game.id}`}>
          <strong>Name:</strong> {game.name}<br />
          {renderImage(game.image, game.name, '200px', '200px')}<br />
          <strong>Price:</strong> ${game.price}<br />
          <strong>Quantity:</strong> {game.quantity} <br />
        </Link>
        <hr />
        <button className="buy" onClick={() => onBuyClick(game, userId)}>Kup</button>
      </li>
    </div>
  );
}

export default GameListItem;
