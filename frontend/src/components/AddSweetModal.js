import React, { useState } from 'react';
import apiService from '../api/apiService';
import './AddSweetModal.css';

const AddSweetModal = ({ onClose, onSuccess }) => {
    const [name, setName] = useState('');
    const [category, setCategory] = useState('');
    const [price, setPrice] = useState(0);
    const [quantity, setQuantity] = useState(0);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const newSweet = { name, category, price, quantity };
        try {
            await apiService.post('/sweets', newSweet);
            onSuccess(); // Call success callback
        } catch (error) {
            console.error("Failed to add sweet", error);
            alert("Failed to add sweet.");
        }
    };

    return (
        <div className="modal-backdrop">
            <div className="modal-content">
                <h2>Add New Sweet</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Name</label>
                        <input type="text" value={name} onChange={e => setName(e.target.value)} required />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <input type="text" value={category} onChange={e => setCategory(e.target.value)} required />
                    </div>
                    <div className="form-group">
                        <label>Price</label>
                        <input type="number" step="0.01" value={price} onChange={e => setPrice(parseFloat(e.target.value))} required />
                    </div>
                    <div className="form-group">
                        <label>Quantity</label>
                        <input type="number" value={quantity} onChange={e => setQuantity(parseInt(e.target.value))} required />
                    </div>
                    <div className="modal-actions">
                        <button type="submit">Add Sweet</button>
                        <button type="button" onClick={onClose}>Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddSweetModal;