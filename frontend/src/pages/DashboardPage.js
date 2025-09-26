import React, { useState, useEffect, useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import apiService from '../api/apiService';
import SweetCard from '../components/SweetCard';
import SearchFilter from '../components/SearchFilter';
import AdminPanel from '../components/AdminPanel';
import { Search, Plus, User, ShoppingBag, X, Edit3 } from 'lucide-react';
import './DashboardPage.css';

const DashboardPage = () => {
    const { user } = useContext(AuthContext);
    const [sweets, setSweets] = useState([]);
    const [loading, setLoading] = useState(true);
    const [searchParams, setSearchParams] = useState({});

    // State for editing a sweet
    const [editingSweet, setEditingSweet] = useState(null);
    const [editForm, setEditForm] = useState({
        name: '',
        category: '',
        price: 0,
        quantity: 0
    });

    // Fetch all sweets from the API
    const fetchSweets = async () => {
        setLoading(true);
        try {
            const query = new URLSearchParams(searchParams).toString();
            const endpoint = query ? `/sweets/search?${query}` : '/sweets';
            const response = await apiService.get(endpoint);
            setSweets(response.data);
        } catch (error) {
            console.error("Failed to fetch sweets", error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchSweets();
    }, [searchParams]);

    // Purchase a sweet
    const handlePurchase = async (sweetId) => {
        try {
            await apiService.post(`/sweets/${sweetId}/purchase`);
            setSweets(prevSweets =>
                prevSweets.map(sweet =>
                    sweet.id === sweetId
                        ? { ...sweet, quantity: sweet.quantity - 1 }
                        : sweet
                )
            );
        } catch (error) {
            console.error("Purchase failed:", error);
            alert("Failed to purchase sweet. It may be out of stock.");
        }
    };

    // Delete a sweet
    const handleDelete = async (sweetId) => {
        if (window.confirm("Are you sure you want to delete this sweet?")) {
            try {
                await apiService.delete(`/sweets/${sweetId}`);
                fetchSweets();
            } catch (error) {
                console.error("Delete failed:", error);
                alert("Failed to delete sweet.");
            }
        }
    };

    // Start editing a sweet
    const handleEdit = (sweet) => {
        setEditingSweet(sweet);
        setEditForm({
            name: sweet.name,
            category: sweet.category,
            price: sweet.price,
            quantity: sweet.quantity,
        });
    };

    // Submit edited sweet
    const submitEdit = async (e) => {
        e.preventDefault();
        try {
            await apiService.put(`/sweets/${editingSweet.id}`, editForm);
            setEditingSweet(null);
            fetchSweets();
        } catch (err) {
            console.error("Failed to update sweet:", err);
            alert("Failed to update sweet.");
        }
    };

    return (
        <div className="dashboard-modern">
            {/* Header Section */}
            <div className="dashboard-header">
                <div className="dashboard-container">
                    <div className="header-content">
                        <div className="header-left">
                            <div>
                                <h1 className="dashboard-title">
                                    <div className="title-icon">
                                        <ShoppingBag className="icon" />
                                    </div>
                                    Sweet Shop Dashboard
                                </h1>
                                {user && (
                                    <div className="welcome-section">
                                        <User className="user-icon" />
                                        <span className="welcome-text">Welcome back, <strong>{user.username}</strong>!</span>
                                        {user.role === 'ADMIN' && (
                                            <span className="admin-badge">Admin</span>
                                        )}
                                    </div>
                                )}
                            </div>
                        </div>
                        
                        <div className="header-stats">
                            <div className="stat-item">
                                <p className="stat-label">Total Products</p>
                                <p className="stat-value">{sweets.length}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            {/* Main Content */}
            <div className="dashboard-main">
                {/* Admin Panel */}
                {user && user.role === 'ADMIN' && (
                    <div className="admin-panel-section">
                        <AdminPanel onSweetAdded={fetchSweets} />
                    </div>
                )}

                {/* Search and Filters */}
                <div className="search-section">
                    <SearchFilter onSearch={setSearchParams} />
                </div>

                {/* Content Section */}
                <div className="content-section">
                    {loading ? (
                        <div className="loading-container">
                            <div className="loading-spinner"></div>
                            <p className="loading-text">Loading delicious sweets...</p>
                        </div>
                    ) : (
                        <>
                            {sweets.length > 0 ? (
                                <>
                                    <div className="content-header">
                                        <h2 className="section-title">
                                            Available Sweets ({sweets.length})
                                        </h2>
                                    </div>
                                    <div className="sweets-grid">
                                        {sweets.map(sweet => (
                                            <SweetCard
                                                key={sweet.id}
                                                sweet={sweet}
                                                onPurchase={handlePurchase}
                                                onDelete={handleDelete}
                                                onEdit={handleEdit}
                                                isAdmin={user.role === 'ADMIN'}
                                            />
                                        ))}
                                    </div>
                                </>
                            ) : (
                                <div className="empty-state">
                                    <div className="empty-icon">
                                        <Search className="search-icon" />
                                    </div>
                                    <h3 className="empty-title">No sweets found</h3>
                                    <p className="empty-description">Try adjusting your search criteria or add some new products.</p>
                                    {user && user.role === 'ADMIN' && (
                                        <button className="add-first-btn">
                                            <Plus className="btn-icon" />
                                            Add First Sweet
                                        </button>
                                    )}
                                </div>
                            )}
                        </>
                    )}
                </div>
            </div>

            {/* Enhanced Edit Modal */}
            {editingSweet && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        {/* Modal Header */}
                        <div className="modal-header">
                            <div className="modal-title-section">
                                <div className="modal-icon">
                                    <Edit3 className="edit-icon" />
                                </div>
                                <div>
                                    <h3 className="modal-title">Edit Sweet</h3>
                                    <p className="modal-subtitle">{editingSweet.name}</p>
                                </div>
                            </div>
                            <button
                                onClick={() => setEditingSweet(null)}
                                className="close-btn"
                            >
                                <X className="close-icon" />
                            </button>
                        </div>

                        {/* Modal Form */}
                        <form onSubmit={submitEdit} className="modal-form">
                            <div className="form-group">
                                <label className="form-label">Sweet Name</label>
                                <input
                                    type="text"
                                    placeholder="Enter sweet name"
                                    value={editForm.name}
                                    onChange={(e) => setEditForm({ ...editForm, name: e.target.value })}
                                    className="form-input"
                                    required
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label">Category</label>
                                <input
                                    type="text"
                                    placeholder="Enter category"
                                    value={editForm.category}
                                    onChange={(e) => setEditForm({ ...editForm, category: e.target.value })}
                                    className="form-input"
                                    required
                                />
                            </div>

                            <div className="form-row">
                                <div className="form-group">
                                    <label className="form-label">Price</label>
                                    <input
                                        type="number"
                                        step="0.01"
                                        placeholder="0.00"
                                        value={editForm.price}
                                        onChange={(e) => setEditForm({ ...editForm, price: parseFloat(e.target.value) })}
                                        className="form-input"
                                        required
                                    />
                                </div>
                                <div className="form-group">
                                    <label className="form-label">Quantity</label>
                                    <input
                                        type="number"
                                        placeholder="0"
                                        value={editForm.quantity}
                                        onChange={(e) => setEditForm({ ...editForm, quantity: parseInt(e.target.value) })}
                                        className="form-input"
                                        required
                                    />
                                </div>
                            </div>

                            <div className="form-actions">
                                <button type="submit" className="save-btn">
                                    Save Changes
                                </button>
                                <button
                                    type="button"
                                    onClick={() => setEditingSweet(null)}
                                    className="cancel-btn"
                                >
                                    Cancel
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
};

export default DashboardPage;