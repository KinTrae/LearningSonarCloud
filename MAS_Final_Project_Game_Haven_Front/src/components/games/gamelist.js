import React, { useState, useEffect } from 'react';
import GameListItem from "./gameListItem";
import CreateGameForm from './createGameForm';
import PurchaseModal from './purchaseModal';
import { getAllGames, getGamesByCategory } from '../../services/gameService';
import { getCategories } from '../../services/categoryService';

const GameList = () => {
  const [games, setGames] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [showPurchaseModal, setShowPurchaseModal] = useState(false);
  const [selectedGame, setSelectedGame] = useState(null);
  const userId = 2; // Zastąp wartością userId z kontekstu aplikacji

  const handleCreateSuccess = (newGame) => {
    setGames((prevGames) => [...prevGames, newGame]);
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  useEffect(() => {
    if (selectedCategory) {
      fetchGamesByCategory(selectedCategory);
    } else {
      fetchGames();
    }
  }, [currentPage, selectedCategory]);

  const fetchGames = async () => {
    try {
      const gamesData = await getAllGames(currentPage, 5);
      setGames(gamesData.content);
      setTotalPages(gamesData.totalPages);
    } catch (error) {
      console.log('Error fetching games: ' + error);
    }
  };

  const fetchGamesByCategory = async (categoryId) => {
    try {
      const gamesData = await getGamesByCategory(categoryId);
      setGames(gamesData);
    } catch (error) {
      console.log('Error fetching games by category: ' + error);
    }
  };

  const fetchCategories = async () => {
    try {
      const categoriesData = await getCategories();
      setCategories(categoriesData);
    } catch (error) {
      console.log('Error fetching categories: ' + error);
    }
  };

  const handleBuyClick = (game) => {
    setSelectedGame(game);
    setShowPurchaseModal(true);
  };

  const handleCloseModal = () => {
    setShowPurchaseModal(false);
    setSelectedGame(null);
  };

  return (
    <div>
      <h1>Game List</h1>
      <div>
        <label htmlFor="categorySelect">Select Category: </label>
        <select
          id="categorySelect"
          onChange={(e) => setSelectedCategory(e.target.value)}
        >
          <option value="">All Categories</option>
          {Array.isArray(categories) && categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>
      </div>
      <div>
        {Array.from({ length: totalPages }, (_, index) => (
          <button key={index} onClick={() => setCurrentPage(index)}>
            {index + 1}
          </button>
        ))}
      </div>
      <ul className="list">
        {games.map((game) => (
          <GameListItem
            key={game.id}
            game={game}
            onBuyClick={handleBuyClick}
            userId={userId}
          />
        ))}
      </ul>
      {showPurchaseModal && selectedGame && (
        <PurchaseModal
          game={selectedGame}
          onClose={handleCloseModal}
          userId={userId}
        />
      )}
    </div>
  );
};

export default GameList;
