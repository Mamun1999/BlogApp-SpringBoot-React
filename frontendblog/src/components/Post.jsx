import React, { useContext } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import { Card, CardBody, CardText, Button } from "reactstrap";
import { getCurrentUserDetail, isLoggedIn } from "../Auth";
import userContext from "../context/userContext";

function Post({post 
  = {  id:-1, title: "This is defefault title", content: "Default content"},deletePost }) {

const userContextData=useContext(userContext)


  const [user,setUser]=useState(null)
  const [login,setLogin]=useState(null)

  useEffect(()=>{
    setUser(getCurrentUserDetail())
    setLogin(isLoggedIn())
  },[])
  return (
    <Card className='border-0 shadow-sm mt-3'>
      <CardBody>
        <h1>{post.title}</h1>
        <CardText dangerouslySetInnerHTML={{ __html: post.content.substring(0, 70) + "...." }}></CardText>

        <div>
          <Link className="btn btn-secondary" to={'/posts/'+post.postId}>Read more</Link>
         { userContextData.user.login && (user && user.id==post.user.id ?  <Button onClick={(event)=>deletePost(post)} color="danger" className="ms-2">Delete</Button>:'' )

        }
        { userContextData.user.login && (user && user.id==post.user.id ?  <Button  color="warning" className="ms-2">Update</Button>:'' )
         
        }
        </div>
      </CardBody>
    </Card>
  );
}

export default Post;
