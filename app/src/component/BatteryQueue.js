import React, { useState, useEffect } from 'react';
import axios from 'axios';
import socketIOClient from 'socket.io-client';
import BatteryNumberAdd from "./BatteryNumberAdd";
import "./Queue.css"

const ENDPOINT = "http://localhost:8080/websocket";

const BatteryQueue = () => {
    const [numbers, setNumbers] = useState([]);
    const [showAddForm, setShowAddForm] = useState(false);

    const socket = socketIOClient(ENDPOINT);

    const fetchNumbers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/batteryinspection/numbers');
            setNumbers(response.data._embedded.batteryInspectNumberList);
        } catch (error) {
            console.error('Error fetching numbers:', error);
        }
    };

    useEffect(() => {
        socket.on("newNumber", (newNumber) => {
            setNumbers(prevNumbers => [...prevNumbers, newNumber]);
        });

        return () => socket.disconnect();
    }, []);

    useEffect(() => {
        fetchNumbers();
    }, []); // Pobranie danych przy załadowaniu komponentu

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

    return (
        <div className="queue">
            <h2>Battery Inspection Numbers</h2>
            <button onClick={toggleAddForm}>Add Number</button> {/* Przycisk do wyświetlenia formularza */}
            {showAddForm && <BatteryNumberAdd />}
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
