import { myAxios } from "./helper";
export const loadAllCategories=()=>{
    return myAxios.get("/category/").then(response=> response.data)

}