import React, { useState, useEffect } from 'react';
import axios from 'axios';

const QueueList = () => {
    const [numbers, setNumbers] = useState([]);

    useEffect(() => {
        const fetchNumbers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/mechanicalinspection/numbers');
                setNumbers(response.data);
            } catch (error) {
                console.error('Błąd podczas pobierania zadań:', error);
            }
        };

        fetchNumbers();
    }, []);
    return (
        <div>
            <h2>Queue</h2>
            <ul>
                {numbers.map(number => (
                    <li key={number.id}>
                        <strong>Number:</strong> {number.id}<br />
                        <strong>Car Name:</strong> {number.carName}<br />
                        <strong>Application time:</strong> {number.time}<br />
                    </li>
                ))}
            </ul>

        </div>
    );
};
export default QueueList;