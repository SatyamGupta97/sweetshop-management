import React, { createContext, useState, useEffect } from 'react';
import apiService from '../api/apiService';
import { jwtDecode } from 'jwt-decode';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem('token'));
  const [user, setUser] = useState(null);

  useEffect(() => {
    // This function runs whenever the 'token' changes.
    if (token) {
      try {
        const decoded = jwtDecode(token);
        // The 'sub' (subject) claim in our JWT holds the username.
        setUser({ username: decoded.sub });
        localStorage.setItem('token', token);
      } catch (e) {
        console.error("Invalid or expired token", e);
        localStorage.removeItem('token');
        setToken(null);
        setUser(null);
      }
    } else {
        localStorage.removeItem('token');
        setUser(null);
    }
  }, [token]);

  const login = async (username, password) => {
    const response = await apiService.post('/auth/login', { username, password });
    setToken(response.data.token);
  };

  const logout = () => {
    setToken(null);
  };

  return (
    <AuthContext.Provider value={{ token, user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export { AuthContext, AuthProvider };