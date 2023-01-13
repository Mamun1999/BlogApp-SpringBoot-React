//isLogged in? if user detail or token is exist in local storage then he is sign in.

export const isLoggedIn=()=>{
    let data=localStorage.getItem("data");
    if(data==null)
     {
        return false;
     }
    else return true;
}

//doLogin=> data set to local storage


export const doLogin=(data,next)=>{
    localStorage.setItem("data",JSON.stringify(data))
    next()

}

//do logout=> remove fromlocal storage

export const doLogOut=(next)=>{
    localStorage.removeItem("data");
    next()//this callback function is taken if user redirect to the page

}

// get current user

export const getCurrentUserDetail=()=>{
    if(isLoggedIn()){
        return JSON.parse(localStorage.getItem("data")).user;
    }
    else{
        return undefined;
    }
}