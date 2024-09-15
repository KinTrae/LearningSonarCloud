import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/games';

const getTokenHeader = () => {
  const token = localStorage.getItem('accessToken');
  return { Authorization: `Bearer ${token}` };
};

export const updateGame = async (formData) => {
  try {
    const response = await axios.put(BASE_URL, formData, {
      headers: getTokenHeader(),
    });
    return response.data;
  } catch (error) {
    console.log('Error updating game: ' + error);
    throw error;
  }
};

export const getGameDetails = async (gameId) => {
    try {
      const response = await axios.get(`${BASE_URL}/${gameId}`);
      return response.data;
    } catch (error) {
      console.log('Error fetching game details: ' + error);
      throw error;
    }
  };

export const deleteGame = async (gameId) => {
    try {
      await axios.delete(`${BASE_URL}/${gameId}`, {
        headers: getTokenHeader(),
      });
    } catch (error) {
      console.log('Error deleting game: ' + error);
      throw error;
    }
  };


export const getAllGames = async (currentPage, pageSize) => {
    try {
      const response = await axios.get(`${BASE_URL}?page=${currentPage}&size=${pageSize}`);
      return response.data;
    } catch (error) {
      console.log('Error fetching games: ' + error);
      throw error;
    }
  };

  export const createGame = async (newGameData) => {
    try {
      const response = await axios.post(BASE_URL, newGameData, {
        
      });
      return response.data;
    } catch (error) {
      console.log('Error creating game: ' + error);
      throw error;
    }
  };

  export const getGamesByCategory = async (categoryId) => {
    try {
      const response = await axios.get(`${BASE_URL}/category/${categoryId}`);
      return response.data;
    } catch (error) {
      console.log('Error fetching games by category: ' + error);
      throw error;
    }
  };
  
  
