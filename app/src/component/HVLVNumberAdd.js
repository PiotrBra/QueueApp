import React, { useState } from 'react';
import axios from 'axios';

const HVLVNumberAdd = ({ onNumberAdded }) => { // Przekazujemy funkcję onNumberAdded jako props
    const [carName, setCarName] = useState('');

    const handleCarNameChange = (event) => {
        setCarName(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            await axios.post('http://localhost:8080/hvlvinspection/numbers', null, {
                params: { carName }
            });
            alert('Number added successfully!');
            setCarName('');
            onNumberAdded(); // Wywołujemy funkcję onNumberAdded po udanym dodaniu numeru
        } catch (error) {
            console.error('Error adding number:', error);
            alert('Error adding number. Please try again.');
        }
    };

    return (
        <div>
            <h2>Add HVLV Inspection Number</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Car Name:
                    <input type="text" value={carName} onChange={handleCarNameChange} />
                </label>
                <button type="submit">Add Number</button>
            </form>
        </div>
    );
};

export default HVLVNumberAdd;
