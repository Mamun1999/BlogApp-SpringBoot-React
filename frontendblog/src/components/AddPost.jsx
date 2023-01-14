import { useState } from "react";
import { useEffect } from "react";
import  JoditEditor from "jodit-react"
import { useRef } from "react";
import {
  Card,
  CardBody,
  Form,
  Label,
  Input,
  Container,
  Button,
} from "reactstrap";
import { loadAllCategories } from "../services/category-service";
import { createPost as doCreatePost } from "../services/post-service";
import { getCurrentUserDetail } from "../Auth";
import { toast } from "react-toastify";
const AddPost = () => {
  const editor = useRef(null);

  const[user,setUser]=useState(undefined)
//   const [content, setContent] = useState("");
  const [categories, setCategories] = useState([]);
  const [post,setPost]=useState({
    title:'',
    content:'',
    categoryId:'' 
  })
//   const config={
//     placeholder:"Start typing"
//   }

  useEffect(() => {
    setUser(getCurrentUserDetail())
    loadAllCategories()
      .then((data) => {
        console.log(data);
        
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const fieldChange=(event)=>{
    // console.log(event.target.name);
    setPost({...post, [event.target.name]:event.target.value})

  }
   const contentFieldChange=(data)=>{
    setPost({...post, 'content':data})
   }

   //create post funvction
    const createPost=(event)=>{
        event.preventDefault();
        // console.log("Form submitted");
        // console.log(post)

        //validation
        if(post.title.trim()===''){
            toast.error("Post title is required")
            return;
        }
        if(post.content.trim()===''){
            toast.error("Content is required")
            return;
        }
        if(post.categoryId===''){
            toast.error("Select")
            return;
        }

        //Sent to the server
        post['userId']=user.id
       doCreatePost(post).then(data=>{
       toast.success("Post created !!")
       setPost({
        title:'',
        content:'',
       categoryId:'' 
       })
        // console.log(post)
       }).catch((error)=>{
        toast.error("Post not created ")
        // console.log(error)
       })
    }


  return (
    <div className="wrapper">
      <Card className="shadow-sm  border-0 mt-2">
        <CardBody>
            {/* {JSON.stringify(post)} */}
          <h3>What is going in your mind</h3>

          <Form onSubmit={createPost}>
            <div className="my-3">
              <Label for="Title"> Post title</Label>
              <Input
                type="text"
                id="title"
                placeholder="Enter here"
                className="rounded-0"
                name="title"
                onChange={(e)=>fieldChange(e)}
              />
            </div>

            <div className="my-3">
              <Label for="content">Post Content</Label>

              <JoditEditor
                ref={editor}
                value={post.content}
                onChange={contentFieldChange}
                // config={config}
              />
              {/* <Input
                type="textarea"
                id="content"
                placeholder="Enter here"
                className="rounded-0"
                style={{ height: "300px" }}
              /> */}
            </div>

            <div className="my-3">
              <Label for="category">Post Category</Label>
              <Input
                type="select"
                id="category"
                placeholder="Enter here"
                className="rounded-0"
                name="categoryId"
                onChange={fieldChange}
                defaultValue={0}
              >
                <option defaultValue={0}>
                  --Select category--
                </option>

                {categories.map((category) => (
                  <option value={category.categoryId} key={category.categoryId}>
                    {category.categoryTitle}
                  </option>
                ))}
              </Input>
            </div>

            <Container className="text-center">
              <Button type="submit" className="rounded-0" color="primary">
                Create Post
              </Button>
              <Button className="rounded-0 ms-2" color="danger">
                Reset Content
              </Button>
            </Container>
          </Form>
        </CardBody>
      </Card>
    </div>
  );
};
export default AddPost;
