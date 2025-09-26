import React, { useState } from 'react';
import './SearchFilter.css';

const SearchFilter = ({ onSearch }) => {
    const [name, setName] = useState('');

    const handleSearch = () => {
        // Pass the search criteria up to the parent component
        onSearch({ name });
    };

    const handleClear = () => {
        setName('');
        onSearch({}); // Clear the search
    };

    return (
        <div className="search-container">
            <input
                type="text"
                placeholder="Search by name..."
                value={name}
                onChange={(e) => setName(e.target.value)}
                className="search-input"
            />
            <button onClick={handleSearch} className="search-button">Search</button>
            <button onClick={handleClear} className="clear-button">Clear</button>
        </div>
    );
};

export default SearchFilter;