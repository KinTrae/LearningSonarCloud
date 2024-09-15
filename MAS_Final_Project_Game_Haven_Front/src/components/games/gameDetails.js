import React, {useState, useEffect} from "react";
import { useParams, useNavigate  } from "react-router-dom"
import {renderImage} from "../../utils/util"
import UpdateGame from "./updateGameForm"
import { getGameDetails } from '../../services/gameService';


const GameDetails = () => {
    const {id} = useParams();
    const [game, setGame] = useState([]);

    useEffect(() => {
      const fetchGame = async () => {
        try {
          const gameDetails = await getGameDetails(id);
          setGame(gameDetails);
        } catch (error) {
          console.log('Error fetching game details: ' + error);
        }
      };
    
      fetchGame();
    }, [id]);
    
    
  const handleUpdateSuccess = (updatedGame) => {
    setGame(updatedGame);
    console.log('Game updated successfully!');
  };

  const history = useNavigate();

  const redirect = (e) => {
      history('/games');
  }

  return (
      <div className="details">
        <button onClick={redirect}>Return to the store</button>
        <hr />
        <div><UpdateGame game={game} onUpdateSuccess={handleUpdateSuccess}/>
        <br />
        <hr />
        </div>
        <h2>Game Details</h2>
        <strong>ID:</strong> {id} <br />
              <strong>Name:</strong> {game.name}<br />
              {renderImage(game.image, game.name, 400,500)}<br />
              <strong>Price:</strong> {game.price}<br />
              <strong>Description:</strong> {game.description} <br />
              <strong>Quantity:</strong> {game.quantity} <br />
              <hr />
      </div>

  );
};

export default GameDetails;
