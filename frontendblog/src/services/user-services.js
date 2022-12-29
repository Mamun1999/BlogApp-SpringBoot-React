import { myAxios } from "./helper"

export const signUp=(user)=>{
  return  myAxios.post("/api/auth/register",user)
    .then((response)=> response.data);
    // here we are using Promise. what data we are sending using post method we will get response in response.json. it is working as callback
};