import React, { useImperativeHandle } from 'react'
import Base from '../components/Base'
import { useNavigate, useParams } from 'react-router-dom'
import { useContext } from 'react'
import userContext from '../context/userContext'
import { useEffect } from 'react'
import { loadPost, updatePostService } from '../services/post-service'
import { toast } from 'react-toastify'
import  JoditEditor from "jodit-react"
import { useRef } from 'react'
import { useState } from 'react'
import { createPost } from '../services/post-service'
import {
  Card,
  CardBody,
  Form,
  Label,
  Input,
  Container,
  Button,
} from "reactstrap";
import { loadAllCategories } from '../services/category-service'

function UpdateBlog() {
  const editor = useRef(null);
    const [categories, setCategories] = useState([]);
    const { blogId }=useParams()
    const object=useContext(userContext)
    const navigate=useNavigate()
    const [post, setPost] = useState(null)
    useEffect(()=>{
     
      loadAllCategories()
      .then((data) => {
        console.log(data);
        
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });

        //load the log from database
        loadPost(blogId).then(data=>{
            setPost({...data,categoryId:data.category.categoryId})


        }).catch(error=>{
            console.log(error)
            toast.error("error in logging")
        })
    },[])
    useEffect(()=>{
        if(post){
            if(post.user.id!= object.user.data.id){
                toast.error("Thi is not your post")
                navigate("/")

            }
        }
    },[post])

    const handleChange=(event, fieldName)=>{
      setPost({
        ...post,[fieldName]:event.target.value
      })
    }

    const updatePost=(event)=>{
      event.preventDefault()
      console.log(post)
      updatePostService({...post, category:{categoryId:post.categoryId}},post.postId).then(res=>{
        console.log(res)
        toast.success("Post updated succesfully")
      }).catch(error=>{
        console.log(error)
        toast.error("Error in updating post")
      })
    }

    const updateHtml=()=>{
        return(
            <div className="wrapper">
              {JSON.stringify(post)}
      <Card className="shadow-sm  border-0 mt-2">
        <CardBody>
            {/* {JSON.stringify(post)} */}
          <h3>Update your post</h3>

          <Form onSubmit={updatePost}>
            <div className="my-3">
              <Label for="Title"> Post title</Label>
              <Input
                type="text"
                id="title"
                placeholder="Enter here"
                className="rounded-0"
                name="title"
                value={post.title}
                onChange={(event)=>handleChange(event,"title")}
              />
            </div>

            <div className="my-3">
              <Label for="content">Post Content</Label>

              <JoditEditor
                ref={editor}

                value={post.content}
               
                onChange={newContent=>setPost({...post, content:newContent })}
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

            <div className="mt-3">
              <Label for="image">Select post banner</Label>
              <Input id="image" type="file"onChange={''}></Input>
            </div>

            <div className="my-3">
              <Label for="category">Post Category</Label>
              <Input
                type="select"
                id="category"
                placeholder="Enter here"
                className="rounded-0"
                name="categoryId"
                onChange={(event)=>handleChange(event,'categoryId')}
                defaultValue={0}
                value={post.categoryId}
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
                Update Post
              </Button>
              <Button className="rounded-0 ms-2" color="danger">
                Reset Content
              </Button>
            </Container>
          </Form>
        </CardBody>
      </Card>
    </div>
        )
    }
  return (
  <Base>
  
  <Container>
  {post && updateHtml()}
  </Container>
   
  </Base>
  )
}

export default UpdateBlog
