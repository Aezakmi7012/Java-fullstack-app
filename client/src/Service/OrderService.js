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

export const latestOrders = async () => {
  return await axios.get(`${API_URL}/orders/latest`, getAuthHeader());
};

export const createOrder = async (order) => {
  return await axios.post(`${API_URL}/orders`, order, getAuthHeader());
};

export const deleteOrder = async (orderId) => {
  return await axios.delete(`${API_URL}/orders/${orderId}`, getAuthHeader());
};
