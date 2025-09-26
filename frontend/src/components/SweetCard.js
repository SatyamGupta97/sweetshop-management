import React from 'react';
import { ShoppingCart, Edit3, Trash2, Package, DollarSign, Tag } from 'lucide-react';
import './SweetCard.css';

const SweetCard = ({ sweet, onPurchase, onDelete, onEdit, isAdmin }) => {
    const isOutOfStock = sweet.quantity === 0;
    const isLowStock = sweet.quantity > 0 && sweet.quantity <= 5;

    return (
        <div className={`sweet-card ${isOutOfStock ? 'out-of-stock' : ''}`}>
            {/* Card Header with Stock Badge */}
            <div className="card-header">
                <div className="sweet-image-placeholder">
                    <Package className="placeholder-icon" />
                </div>
                <div className={`stock-badge ${isOutOfStock ? 'stock-empty' : isLowStock ? 'stock-low' : 'stock-good'}`}>
                    {isOutOfStock ? 'Out of Stock' : isLowStock ? 'Low Stock' : 'In Stock'}
                </div>
            </div>

            {/* Card Content */}
            <div className="card-content">
                <div className="sweet-info">
                    <h3 className="sweet-name">{sweet.name}</h3>
                    
                    <div className="sweet-meta">
                        <div className="meta-item">
                            <Tag className="meta-icon" />
                            <span className="sweet-category">{sweet.category}</span>
                        </div>
                    </div>

                    <div className="sweet-details">
                        <div className="price-section">
                            <DollarSign className="price-icon" />
                            <span className="sweet-price">${sweet.price.toFixed(2)}</span>
                        </div>
                        
                        <div className="quantity-section">
                            <Package className="quantity-icon" />
                            <span className="quantity-text">
                                {sweet.quantity} {sweet.quantity === 1 ? 'left' : 'left'}
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            {/* Card Actions */}
            <div className="card-actions">
                <button
                    className={`purchase-btn ${isOutOfStock ? 'btn-disabled' : 'btn-primary'}`}
                    disabled={isOutOfStock}
                    onClick={() => onPurchase(sweet.id)}
                >
                    <ShoppingCart className="btn-icon" />
                    {isOutOfStock ? 'Out of Stock' : 'Purchase'}
                </button>

                {isAdmin && (
                    <div className="admin-actions">
                        <button 
                            className="edit-btn"
                            onClick={() => onEdit(sweet)}
                            title="Edit sweet"
                        >
                            <Edit3 className="btn-icon" />
                            Edit
                        </button>
                        <button 
                            className="delete-btn"
                            onClick={() => onDelete(sweet.id)}
                            title="Delete sweet"
                        >
                            <Trash2 className="btn-icon" />
                            Delete
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
};

export default SweetCard;