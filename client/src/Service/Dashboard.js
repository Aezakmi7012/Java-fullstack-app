import axios from "axios";

const API_URL = 'http://localhost:8080';

const getAuthHeader = () => {
  const token = localStorage.getItem('token');
  return token ? {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  } : {};
};

export const fetchDashboardData = async () => {
  return await axios.get(`${API_URL}/dashboard`, getAuthHeader());
};
