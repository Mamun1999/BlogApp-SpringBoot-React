
import './App.css';


import 'bootstrap/dist/css/bootstrap.min.css';

import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';

import About from './pages/About';
import Signup from './pages/Signup';

function App() {
  return (
   
     <BrowserRouter>
     <Routes>
      <Route path="/" element={ <Home/>}/>
      <Route path="login" element={<Login/> }/>
      <Route path="signup" element={<Signup/> }/>
      <Route path="about" element={ <About/>}/>
     </Routes>

     </BrowserRouter>

  );
}

export default App;
