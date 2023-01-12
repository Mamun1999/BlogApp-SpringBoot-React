import { myAxios } from "./helper"

export const signUp=(user)=>{
  return  myAxios.post("/api/auth/register",user)
    .then((response)=> response.data);
    // here we are using Promise. what data we are sending using post method we will get response in response.data. it is working as callback
    // and in response data either success or error. if error then we wil show the error in Signup.js page.
};

export const userLogin=(loginDetail)=>{

  return myAxios.post("/api/auth/login",loginDetail).then((response)=>response.data);

}