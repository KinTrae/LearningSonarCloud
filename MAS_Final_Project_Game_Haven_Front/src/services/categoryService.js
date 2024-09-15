import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/categories';

export const getCategories = async () => {
  try {
    const response = await axios.get(BASE_URL);
    return response.data;
  } catch (error) {
    console.log('Error fetching categories: ' + error);
    throw error;
  }
};
