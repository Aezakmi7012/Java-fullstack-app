import axios from 'axios';

const API_URL = 'http://localhost:8080';

const getAuthHeader = () => {
  const token = localStorage.getItem('token');
  return token ? {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  } : {};
};

export const addItem = async (item) => {
  return await axios.post(`${API_URL}/admin/items`, item, getAuthHeader());
};

export const deleteItems = async (itemId) => {
  return await axios.delete(`${API_URL}/admin/items/${itemId}`, getAuthHeader());
};

export const fetchItems = async () => {
  return await axios.get(`${API_URL}/items`, getAuthHeader());
};
