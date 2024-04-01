// HVLVQueue.js
import React, {useEffect, useState} from 'react';
import axios from "axios";
import HVLVNumberAdd from "./HVLVNumberAdd";

function HVLVQueue() {
    const [numbers, setNumbers] = useState([]);
    const [showAddForm, setShowAddForm] = useState(false);


    const fetchNumbers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/hvlvinspection/numbers');
            setNumbers(response.data._embedded.hVLVInspectNumberList);
        } catch (error) {
            console.error('Error fetching numbers:', error);
        }
    };



    useEffect(() => {
        fetchNumbers();
    }, []); // Pobranie danych przy załadowaniu komponentu

    const addNumber = async (carName) => {
        try {
            // Wyślij żądanie dodania numeru
            await axios.post('http://localhost:8080/hvlvinspection/numbers', { carName });

            // Po dodaniu numeru, zaktualizuj dane komponentu pobierając najnowsze dane z serwera
            fetchNumbers();
        } catch (error) {
            console.error('Error adding number:', error);
        }
    };

    const toggleAddForm = () => {
        setShowAddForm(!showAddForm); // Funkcja do przełączania widoczności formularza
    };

    const handleNumberAdded = () => {
        fetchNumbers(); // Pobranie danych po dodaniu numeru
    };

    return (
        <div className="queue">
            <h2>HVLV Inspection Numbers</h2>
            <button onClick={toggleAddForm}>Add Number</button> {/* Przycisk do wyświetlenia formularza */}
            {showAddForm && <HVLVNumberAdd onNumberAdded={handleNumberAdded} />}
            <ul>
                {numbers.map((number) => (
                    <li key={number.id}>
                        <strong>numer: {number.id}; nazwa auta: {number.carName}</strong>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default HVLVQueue;
