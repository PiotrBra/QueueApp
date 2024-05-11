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
            setNumbers(response.data._embedded.mechInspectNumberList);
        } catch (error) {
            console.error('Error fetching numbers:', error);
            setNumbers([]); // Ustawianie pustej tablicy w przypadku błędu
        }
    };


    useEffect(() => {
        fetchNumbers(); // Wywołanie funkcji pobierającej dane przy pierwszym renderowaniu
        const interval = setInterval(fetchNumbers, 500); // Odświeżanie co 0.5 sekundy

        // Czyszczenie interwału po odmontowaniu komponentu
        return () => clearInterval(interval);
    }, []);


    const toggleAddForm = () => {
        setShowAddForm(!showAddForm); // Funkcja do przełączania widoczności formularza
    };

    const handleNumberAdded = () => {
        fetchNumbers(); // Pobranie danych po dodaniu numeru
    };

    return (
        <div className="queue">
            <h2>HVLV Inspection Numbers</h2>
            <button onClick={toggleAddForm}>Add Number</button>
            {/* Przycisk do wyświetlenia formularza */}
            {showAddForm && <HVLVNumberAdd onNumberAdded={handleNumberAdded}/>}
            <ul>
                {numbers ? (
                    numbers.map((number) => (
                        <li key={number.id}>
                            <strong>numer: {number.id}; nazwa auta: {number.carName}</strong>
                        </li>
                    ))
                ) : (
                    <li>Kolejka jest pusta</li>
                )}
            </ul>
        </div>
    );
}

export default HVLVQueue;
