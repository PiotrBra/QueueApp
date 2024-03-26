
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import QueueList from "./component/QueueList";
function App() {
  return (
    <div className="App">
        <header className="app-header">
              <h2>Sqrut FSP queue</h2>
        </header>
        <div className="app-body">
            {/*................................TO DO.........................*/}
            <Router>
                <Routes>
                    <Route exact path ="/" element={<App/>}/>
                    <Route path = "/mechanical/inspection" element={<QueueList/>}/>
                    <Route path = "/battery/inspection" element={<QueueList/>}/>
                    <Route path = "/battery/inspection" element={<QueueList/>}/>
                    <Route path = "/HVLV/inspection" element={<QueueList/>}/>
                </Routes>
            </Router>
        </div>
    </div>
  );
}

export default App;
