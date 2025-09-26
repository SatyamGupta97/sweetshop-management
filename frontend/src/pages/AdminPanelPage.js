import React, { useEffect, useState, useContext } from 'react';
import apiService from '../api/apiService';
import { AuthContext } from '../context/AuthContext';
import { Users, Shield, Crown, Loader2, UserPlus, AlertCircle } from 'lucide-react';
import './AdminPanelPage.css';

const AdminPanelPage = () => {
  const { token } = useContext(AuthContext);
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [promoting, setPromoting] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      setError('');
      const response = await apiService.get('/users', {
        headers: { Authorization: `Bearer ${token}` },
      });
      setUsers(response.data);
      setLoading(false);
    } catch (err) {
      console.error(err);
      setError('Failed to load users. Please try again.');
      setLoading(false);
    }
  };

  const promoteUser = async (userId) => {
    try {
      setPromoting(userId);
      setError('');
      await apiService.put(`/users/${userId}/promote`, null, {
        headers: { Authorization: `Bearer ${token}` },
      });
      await fetchUsers();
    } catch (err) {
      console.error(err);
      setError('Failed to promote user. Please try again.');
    } finally {
      setPromoting(null);
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-gradient-to-br from-slate-50 via-blue-50 to-indigo-100 flex items-center justify-center">
        <div className="bg-white rounded-2xl shadow-xl p-8 flex items-center space-x-4">
          <Loader2 className="h-8 w-8 animate-spin text-blue-600" />
          <div>
            <p className="text-xl font-semibold text-gray-900">Loading Admin Panel</p>
            <p className="text-gray-600">Fetching user data...</p>
          </div>
        </div>
      </div>
    );
  }

  const adminCount = users.filter(user => user.role === 'ADMIN').length;
  const regularUserCount = users.length - adminCount;

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 via-blue-50 to-indigo-100 p-8">
      {/* Header */}
      <div className="flex items-center space-x-4 mb-8">
        <div className="bg-gradient-to-r from-blue-600 to-indigo-600 p-3 rounded-2xl shadow-lg">
          <Shield className="h-8 w-8 text-white" />
        </div>
        <div>
          <h1 className="text-4xl font-bold bg-gradient-to-r from-gray-900 to-gray-700 bg-clip-text text-transparent">
            Admin Panel
          </h1>
          <p className="text-gray-600 text-lg">Manage user accounts and permissions</p>
        </div>
      </div>

      {/* Error */}
      {error && (
        <div className="mb-6 bg-red-50 border-l-4 border-red-400 p-4 rounded-lg shadow-sm flex items-center">
          <AlertCircle className="h-5 w-5 text-red-400 mr-3" />
          <p className="text-red-800 font-medium">{error}</p>
        </div>
      )}

      {/* Stats */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">
        <div className="card-hover p-6 rounded-2xl shadow-custom-xl bg-white flex justify-between items-center">
          <div>
            <p className="text-gray-600 text-sm font-medium mb-1">Total Users</p>
            <p className="text-3xl font-bold gradient-text-blue">{users.length}</p>
          </div>
          <div className="icon-bg-blue p-3 rounded-xl">
            <Users className="h-8 w-8 text-white" />
          </div>
        </div>

        <div className="card-hover p-6 rounded-2xl shadow-custom-xl bg-white flex justify-between items-center">
          <div>
            <p className="text-gray-600 text-sm font-medium mb-1">Administrators</p>
            <p className="text-3xl font-bold gradient-text-purple">{adminCount}</p>
          </div>
          <div className="icon-bg-purple p-3 rounded-xl">
            <Crown className="h-8 w-8 text-white" />
          </div>
        </div>

        <div className="card-hover p-6 rounded-2xl shadow-custom-xl bg-white flex justify-between items-center">
          <div>
            <p className="text-gray-600 text-sm font-medium mb-1">Regular Users</p>
            <p className="text-3xl font-bold gradient-text-green">{regularUserCount}</p>
          </div>
          <div className="icon-bg-green p-3 rounded-xl">
            <UserPlus className="h-8 w-8 text-white" />
          </div>
        </div>
      </div>

      {/* Users Table */}
      <div className="admin-table-card">
        <div className="table-header">
          <h3 className="text-xl font-bold text-gray-900">User Management</h3>
          <p className="text-gray-500 text-sm">Manage user roles and permissions</p>
        </div>
        <div className="overflow-x-auto">
          <table className="admin-table w-full">
            <thead>
              <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {users.map(user => (
                <tr key={user.id}>
                  <td>#{user.id}</td>
                  <td className="username-cell">
                    <div className="flex items-center gap-3">
                      <div className="user-avatar">{user.username.charAt(0).toUpperCase()}</div>
                      <span>{user.username}</span>
                    </div>
                  </td>
                  <td>
                    {user.role === 'ADMIN' ? (
                      <span className="badge-admin">
                        <Crown className="h-4 w-4 mr-1" /> Admin
                      </span>
                    ) : (
                      <span className="badge-user">User</span>
                    )}
                  </td>
                  <td>
                    {user.role !== 'ADMIN' ? (
                      <button
                        className="promote-btn"
                        onClick={() => promoteUser(user.id)}
                        disabled={promoting === user.id}
                      >
                        {promoting === user.id ? 'Promoting...' : 'Promote'}
                      </button>
                    ) : (
                      <span className="text-gray-400 font-medium">—</span>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default AdminPanelPage;
