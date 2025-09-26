import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import { User, LogOut, Shield } from 'lucide-react';
import './Navbar.css';

const Navbar = () => {
  const { user, logout } = useContext(AuthContext);

  return (
    <nav className="navbar">
      <Link to="/" className="nav-brand">
        <div className="brand-icon">🍭</div>
        <span>Sweet Shop</span>
      </Link>
      <div className="nav-links">
        {user ? (
          <>
            <div className="user-welcome">
              <div className="user-avatar">
                <User size={16} />
              </div>
              <span className="welcome-text">Welcome, {user.name || user.email}</span>
            </div>
            {user.role === "ADMIN" && (
              <Link to="/admin" className="nav-link admin-link">
                <Shield size={16} />
                <span>Admin Panel</span>
              </Link>
            )}
            <button onClick={logout} className="nav-button logout-btn">
              <LogOut size={16} />
              <span>Logout</span>
            </button>
          </>
        ) : (
          <>
            <Link to="/login" className="nav-link login-link">Login</Link>
            <Link to="/register" className="nav-link register-link">Register</Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;