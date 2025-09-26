import React, { useState } from 'react';
import AddSweetModal from './AddSweetModal';

const AdminPanel = ({ onSweetAdded }) => {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleSuccess = () => {
        setIsModalOpen(false);
        onSweetAdded(); // This will trigger a re-fetch in DashboardPage
    };

    return (
        <div className="admin-panel">
            <h3>Admin Controls</h3>
            <button onClick={() => setIsModalOpen(true)}>Add New Sweet</button>

            {isModalOpen && (
                <AddSweetModal
                    onClose={() => setIsModalOpen(false)}
                    onSuccess={handleSuccess}
                />
            )}
        </div>
    );
};

export default AdminPanel;