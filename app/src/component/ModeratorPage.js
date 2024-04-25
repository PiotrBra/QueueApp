import React, { useState } from 'react';
import axios from 'axios';

function ModeratorPage() {
    const [numberToRemoveBattery, setNumberToRemoveBattery] = useState('');
    const [numberToRemoveMechanical, setNumberToRemoveMechanical] = useState('');
    const [numberToRemoveHVLV, setNumberToRemoveHVLV] = useState('');

    const handleSubmitBattery = async (event) => {
        event.preventDefault();
        try {
            await axios.delete(`http://localhost:8080/batteryinspection/numbers?id=${numberToRemoveBattery}`);
            setNumberToRemoveBattery('');
        } catch (error) {
            console.error('Error removing number from BatteryQueue:', error);
        }
    };

    const handleSubmitMechanical = async (event) => {
        event.preventDefault();
        try {
            await axios.delete(`http://localhost:8080/mechanicalinspection/numbers?id=${numberToRemoveMechanical}`);
            setNumberToRemoveMechanical('');
        } catch (error) {
            console.error('Error removing number from MechanicalQueue:', error);
        }
    };

    const handleSubmitHVLV = async (event) => {
        event.preventDefault();
        try {
            await axios.delete(`http://localhost:8080/hvlvinspection/numbers?id=${numberToRemoveHVLV}`);
            setNumberToRemoveHVLV('');
        } catch (error) {
            console.error('Error removing number from HVLVQueue:', error);
        }
    };

    const handleInputChangeBattery = (event) => {
        setNumberToRemoveBattery(event.target.value);
    };

    const handleInputChangeMechanical = (event) => {
        setNumberToRemoveMechanical(event.target.value);
    };

    const handleInputChangeHVLV = (event) => {
        setNumberToRemoveHVLV(event.target.value);
    };

    return (
        <div>
            <h2>Moderator Panel</h2>
            <form onSubmit={handleSubmitBattery}>
                <label>
                    Remove number from Battery Queue:
                    <input type="text" value={numberToRemoveBattery} onChange={handleInputChangeBattery} />
                </label>
                <button type="submit">Remove</button>
            </form>

            <form onSubmit={handleSubmitMechanical}>
                <label>
                    Remove number from Mechanical Queue:
                    <input type="text" value={numberToRemoveMechanical} onChange={handleInputChangeMechanical} />
                </label>
                <button type="submit">Remove</button>
            </form>

            <form onSubmit={handleSubmitHVLV}>
                <label>
                    Remove number from HVLV Queue:
                    <input type="text" value={numberToRemoveHVLV} onChange={handleInputChangeHVLV} />
                </label>
                <button type="submit">Remove</button>
            </form>
        </div>
    );
}

export default ModeratorPage;
