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

export const addUser = async (user) => {
  return await axios.post(`${API_URL}/admin/register`, user, getAuthHeader());
};

export const deleteUser = async (id) => {
  return await axios.delete(`${API_URL}/admin/users/${id}`, getAuthHeader());
};

export const fetchUsers = async () => {
  return await axios.get(`${API_URL}/admin/users`, getAuthHeader());
};
