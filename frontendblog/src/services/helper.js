import axios from "axios";
export const BASE_URL='http://localhost:8184';

export const myAxios= axios.create({
    baseURL:BASE_URL
    // using the myAxios variable we will be able to call the server
})