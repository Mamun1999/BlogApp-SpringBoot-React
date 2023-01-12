import React from 'react'
import { Outlet, Navigate } from 'react-router-dom'
import { isLoggedIn } from '../Auth'

const PrivateRoute=()=> {

    // return isLoggedIn() ? <Outlet/> : <Navigate to={"/login"}/>

    // let loggedIn=false;

    if(isLoggedIn()){
        return <Outlet/>
       
    }
    else{
        return <Navigate to={"/login"}/>;
    }

  
}

export default PrivateRoute
