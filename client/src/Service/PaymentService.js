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

export const createRazorpayOrder = async (data) => {
  return await axios.post(`${API_URL}/payments/create-order`, data, getAuthHeader());
};

export const verifyPayment = async (paymentData) => {
  return await axios.post(`${API_URL}/payments/verify`, paymentData, getAuthHeader());
};
