// BatteryQueue.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import BatteryNumberAdd from "./BatteryNumberAdd";
import "./Queue.css"



const BatteryQueue = () => {
    const [numbers, setNumbers] = useState([]);
    const [showAddForm, setShowAddForm] = useState(false);


    const fetchNumbers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/batteryinspection/numbers');
            setNumbers(response.data._embedded.batteryInspectNumberList);
        } catch (error) {
            console.error('Error fetching numbers:', error);
        }
    };



    // Automatyczne odświeżanie danych co jakiś czas
    useEffect(() => {
        const interval = setInterval(() => {
            fetchNumbers();
        }, 5000); // Odświeżanie co 5 sekund (możesz dostosować tę wartość do własnych potrzeb)

        // Czyszczenie interwału po odmontowaniu komponentu
        return () => clearInterval(interval);
    }, []);


    const addNumber = async (carName) => {
        try {
            // Wyślij żądanie dodania numeru
            await axios.post('http://localhost:8080/batteryinspection/numbers', { carName });

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
            <h2>Battery Inspection Numbers</h2>
            <button onClick={toggleAddForm}>Add Number</button> {/* Przycisk do wyświetlenia formularza */}
            {showAddForm && <BatteryNumberAdd onNumberAdded={handleNumberAdded} />}
            <ul>
                {numbers.map((number) => (
                    <li key={number.id}>
                        <strong>numer: {number.id}; nazwa auta: {number.carName}</strong>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default BatteryQueue;
