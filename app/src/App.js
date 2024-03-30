import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Link, Routes} from 'react-router-dom';
import MechanicalQueue from './component/MechanicalQueue';
import BatteryQueue from './component/BatteryQueue';
import HVLVQueue from './component/HVLVQueue';

function App() {
    return (
        <Router>
            <div className="app">
                <h1>sqrut</h1>
                <nav>
                    <ul>
                        <li><Link to="/mechanical">Mechanical Queue</Link></li>
                        <li><Link to="/battery">Battery Queue</Link></li>
                        <li><Link to="/hvlv">HVLV Queue</Link></li>
                    </ul>
                </nav>
                <Routes>
                    <Route index path="/"/>
                    <Route path="/mechanical" element={< MechanicalQueue />}/>
                    <Route path="/battery" element={< BatteryQueue />}/>
                    <Route path="/hvlv" element={< HVLVQueue />}/>
                </Routes>

            </div>
        </Router>
    );
}

export default App;
