import React from 'react'
import { useEffect } from 'react'
import Base from './Base'
import { useParams } from 'react-router-dom'
import { Container, Row, Col } from 'reactstrap'
import CategorySideMenu from './CategorySideMenu'
import { loadAllCategories } from '../services/category-service'
import { loadPostCategoryWise } from '../services/post-service'
import { useState } from 'react'
import { toast } from 'react-toastify'
import Post from './Post'
import { deletePostService } from '../services/post-service'
function Categories() {

 const [posts,setPosts]=useState([])

    const{categoryId}=useParams()
    useEffect(()=>{
        console.log(categoryId)
        loadPostCategoryWise(categoryId).then(data=>{
         setPosts([...data])
        })
        .catch(error=>{
            console.log(error)
            toast.error("Error in loading post")
        })
        },[categoryId])

        function deletePost(posts){
            //deleting post
            deletePostService(posts.postId).then(response=>{
              console.log(response)
              toast.success("post deleted")
             let newPosts= posts.filter(p=>p.postId!=posts.postId)
             setPosts({...newPosts})
            }).catch(error=>{
              console.log(error)
            //   toast.error("Error in deleteing post")
            })
          }      
    
  return (
   <Base>
   
   <Container className="mt-3-3">
         <Row>
          <Col md={2} className="pt-5">
            <CategorySideMenu/>

          </Col>

          <Col md={10}>
               <h1>Blogs count ({posts.length})</h1>
          {
                posts && posts.map((post,index)=>{
                 return(
                    <Post deletePost={deletePost} key={index} post={post}/>
                 )
                })
               }

               {posts.length<=0 ? <h1>No post in this category</h1>:''}

          </Col>
         </Row>

         </Container>
          
   </Base>
  )
}

export default Categories
