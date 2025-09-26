import React, { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';

const DashboardPage = () => {
    const { user, logout } = useContext(AuthContext);

    return (
        <div>
            <h2>Dashboard</h2>
            {user && <h3>Welcome, {user.username}!</h3>}
            <button onClick={logout}>Logout</button>
        </div>
    );
};

export default DashboardPage;