
import './App.css';


import 'bootstrap/dist/css/bootstrap.min.css';

import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';

import About from './pages/About';
import Signup from './pages/Signup';
import Services from './pages/Services';
import UserDashboard from './pages/PrivateRoute/UserDashboard'; 
import { ToastContainer } from 'react-toastify';
  import 'react-toastify/dist/ReactToastify.css';
import PrivateRoute from './components/PrivateRoute';
import ProfileInfo from './pages/PrivateRoute/ProfileInfo';


function App() {
  return (
   
     <BrowserRouter>
     <ToastContainer position='top-center'/>
     <Routes>
      <Route path="/" element={ <Home/>}/>
      <Route path="/login" element={<Login/> }/>
      <Route path="/signup" element={<Signup/> }/>
      <Route path="/about" element={ <About/>}/>
      <Route path="/services" element={<Services/>}/>
      
      <Route path="/user" element={<PrivateRoute/>}>
      <Route path="dashboard" element={<UserDashboard/>}/>
      <Route path="profile-info" element={<ProfileInfo/>}/>
      </Route>
     </Routes>

     </BrowserRouter>

  );
}

export default App;
