import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import apiService from '../api/apiService';
import { User, Lock, Eye, EyeOff, UserPlus } from 'lucide-react';
import './RegisterPage.css';

const RegisterPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await apiService.post('/auth/register', { username, password });
            navigate('/login'); // Redirect to login page after registration
        } catch (error) {
            console.error('Registration failed:', error);
        }
    };

    return (
        <div className="register-container">
            <div className="register-card">
                <div className="register-header">
                    <div className="brand-section">
                        <div className="brand-icon">🍭</div>
                        <h1 className="brand-title">Sweet Shop</h1>
                    </div>
                    <h2 className="register-title">Create Account</h2>
                    <p className="register-subtitle">Join our sweet community</p>
                </div>

                <form onSubmit={handleSubmit} className="register-form">
                    <div className="input-group">
                        <label className="input-label">Username</label>
                        <div className="input-wrapper">
                            <User className="input-icon" size={20} />
                            <input 
                                type="text" 
                                value={username} 
                                onChange={(e) => setUsername(e.target.value)} 
                                required 
                                className="form-input"
                                placeholder="Choose a username"
                            />
                        </div>
                    </div>

                    <div className="input-group">
                        <label className="input-label">Password</label>
                        <div className="input-wrapper">
                            <Lock className="input-icon" size={20} />
                            <input 
                                type={showPassword ? "text" : "password"} 
                                value={password} 
                                onChange={(e) => setPassword(e.target.value)} 
                                required 
                                className="form-input password-input"
                                placeholder="Create a password"
                            />
                            <button 
                                type="button" 
                                className="password-toggle"
                                onClick={() => setShowPassword(!showPassword)}
                            >
                                {showPassword ? <EyeOff size={20} /> : <Eye size={20} />}
                            </button>
                        </div>
                    </div>

                    <button type="submit" className="register-button">
                        <UserPlus size={20} />
                        <span>Create Account</span>
                    </button>
                </form>

                <div className="register-footer">
                    <p className="login-prompt">
                        Already have an account? <a href="/login" className="login-link">Sign in</a>
                    </p>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;