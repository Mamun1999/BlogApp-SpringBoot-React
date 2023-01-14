import './App.css';


import 'bootstrap/dist/css/bootstrap.min.css';

import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './pages/Login';
import Home from './pages/Home';

import About from './pages/About';
import Signup from './pages/Signup';
import Services from './pages/Services';
import UserDashboard from './pages/UserRoute/UserDashboard'; 
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import PrivateRoute from './components/PrivateRoute';
import ProfileInfo from './pages/UserRoute/ProfileInfo';
import PostPage from './pages/PostPage';
import UserProvider from './context/UserProvider';
import Categories from './components/Categories';
import UpdateBlog from './pages/UpdateBlog';

function App() {
  return (
   
     <UserProvider>
      <BrowserRouter>
     <ToastContainer position='top-center'/>
     <Routes>
      <Route path="/" element={ <Home/>}/>
      <Route path="/login" element={<Login/> }/>
      <Route path="/signup" element={<Signup/> }/>
      <Route path="/about" element={ <About/>}/>
      <Route path="/services" element={<Services/>}/>
      <Route path="/posts/:postId" element={<PostPage/>}/>
      <Route path="/categories/:categoryId" element={<Categories/>}/>
    
    <Route path="/user" element={<PrivateRoute/>}>
      <Route path="dashboard" element={<UserDashboard/>}/>
      <Route path="profile-info/:userId" element={<ProfileInfo/>}/>
      <Route path="update-blog/:blogId" element={<UpdateBlog/>}/>
      </Route>
     </Routes>

     </BrowserRouter>
    </UserProvider>

  );
}

export default App;






// import './App.css';


// import 'bootstrap/dist/css/bootstrap.min.css';

// import {BrowserRouter, Routes, Route} from 'react-router-dom';
// import Login from './pages/Login';
// import Home from './pages/Home';

// import About from './pages/About';
// import Signup from './pages/Signup';
// import Services from './pages/Services';
// import UserDashboard from './pages/UserRoute/UserDashboard'; 
// import { ToastContainer } from 'react-toastify';
// import 'react-toastify/dist/ReactToastify.css';
// import PrivateRoute from './components/PrivateRoute';
// import ProfileInfo from './pages/UserRoute/ProfileInfo';


// function App() {
//   return (
   
//      <BrowserRouter>
//      <ToastContainer position='top-center'/>
//      <Routes>
//       <Route path="/" element={ <Home/>}/>
//       <Route path="/login" element={<Login/> }/>
//       <Route path="/signup" element={<Signup/> }/>
//       <Route path="/about" element={ <About/>}/>
//       <Route path="/services" element={<Services/>}/>
      
//       <Route path="/user" element={<PrivateRoute/>}>
//       <Route path="dashboard" element={<UserDashboard/>}/>
//       <Route path="profile-info" element={<ProfileInfo/>}/>
//       </Route>
//      </Routes>

//      </BrowserRouter>

//   );
// }

// export default App;
