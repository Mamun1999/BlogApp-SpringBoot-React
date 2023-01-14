import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import { toast } from 'react-toastify'
import { Container } from 'reactstrap'
import { getCurrentUserDetail } from '../../Auth'
import AddPost from '../../components/AddPost'
import Base from '../../components/Base'
import Post from '../../components/Post'
import { loadPostUserWise } from '../../services/post-service'
import { deletePostService } from '../../services/post-service'
const UserDashboard =()=> {

  const [user, setUser]=useState({})

  const[post,setPost]=useState([])
  useEffect(()=>{
    setUser(getCurrentUserDetail())

    loadPostData()
  },[])
  function loadPostData(){
    loadPostUserWise(getCurrentUserDetail().id).then(data=>{
      console.log(data)
      setPost([...data])
    }).catch(error=>{
      console.log(error)
      toast.error("user post not loaded")
    })
  }



  //delete post function

  function deletePost(post){
    //deleting post
    deletePostService(post.postId).then(response=>{
      console.log(response)
      toast.success("post deleted")
     let newPosts= post.filter(p=>p.postId!=post.postId)
     setPost({...newPosts})
    }).catch(error=>{
      console.log(error)
      // toast.error("Error in deleteing post")
    })
  }
  return (
    <Base>
      <Container>
        <AddPost/>

        <h1 className='my-3'>Posts count : ({post.length})</h1>

        {post.map((post,index)=>{
          return (
            <Post post={post} key={index} deletePost={deletePost}/>
          )
        })}
      </Container>
    </Base>
  )
}

export default UserDashboard
