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

export const addCategory = async (category) => {
  return await axios.post(`${API_URL}/admin/categories`, category, getAuthHeader());
};

export const deleteCategory = async (categoryId) => {
  return await axios.delete(`${API_URL}/admin/categories/${categoryId}`, getAuthHeader());
};

export const fetchCategories = async () => {
  return await axios.get(`${API_URL}/categories`, getAuthHeader());
};
