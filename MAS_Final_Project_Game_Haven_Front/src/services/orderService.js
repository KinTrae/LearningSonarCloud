import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/orders';
const USER_ORDERS_URL = 'http://localhost:8080/api/users/userorders';

const getTokenHeader = () => {
  const token = localStorage.getItem('accessToken');
  return { Authorization: `Bearer ${token}` };
};

export const fetchOrder = async (orderId) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/${orderId}`, {
      headers: getTokenHeader(),
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const deleteOrderedGameFromOrder = async (orderId, orderedGameId) => {
  try {
    const response = await axios.delete(`${API_BASE_URL}/${orderId}/${orderedGameId}`, {
      headers: getTokenHeader(),
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const fetchCurrentOrder = async (userId) => {
  try {
    console.log(`${API_BASE_URL}/current/${userId}`)

    const response = await axios.get(`${API_BASE_URL}/current/${userId}`, {
      headers: getTokenHeader(),
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const cancelOrder = async (orderId) => {
  try {
    const response = await axios.put(`${API_BASE_URL}/cancel/${orderId}`, {}, {
      headers: getTokenHeader(),
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const sendOrder = async (orderId) => {
  try {
    const response = await axios.put(`${API_BASE_URL}/send/${orderId}`, {}, {
      headers: getTokenHeader(),
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const fetchOrders = async (currentPage) => {
  try {
    const response = await axios.get(`${API_BASE_URL}?page=${currentPage}`, {
      headers: getTokenHeader(),
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};

export const fetchUserOrders = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/user/2`, {
      headers: getTokenHeader(),
    });

    return response.data;
  } catch (error) {
    throw error;
  }
};