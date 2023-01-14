import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import { toast } from "react-toastify";
import { Col, Row, Pagination, PaginationItem, PaginationLink, Container } from "reactstrap";
import { loadAllPost } from "../services/post-service";
import Post from "./Post";

function NewFeed() {
  const [postContent, setPostContent] = useState({
    contents:[],
    totalPages: '',
        totalElements: '',
        pageSize: '',
        lastPage: false,
        pageNumber: ''
  });
  // useEffect(()=>{
  //     //load all post from server
  // },[])

  useEffect(() => {
    //load all post from server

   changePage(0)
  }, []);

  const changePage=(pageNumber=0, pageSize=5)=>{
    if(pageNumber > postContent.pageNumber && postContent.lastPage){
      return
    }
    if(pageNumber < postContent.pageNumber && postContent.pageNumber==0){
      return
    }
    loadAllPost(pageNumber,pageSize).then(data=>{
      setPostContent(data)
      console.log(data)
      window.scroll(0,0)
    }).catch(error=>{
      toast.error("Error in loaing post")
    })
  }

  return (
    <div className="container-fluid">
      <Row>
        <Col md={{ size: 10, offset:1 }}>
          <h1>Blogs count ({postContent.totalElements})</h1>
          {
                            postContent.contents?.map((post) => (
                                <Post  post={post}  />
                                //key={post.postId}
                            )) 
                        }

                        <Container className="mt-3">
                          <Pagination size="lg">
                            <PaginationItem onClick={()=>changePage(postContent.pageNumber-1)} disabled={postContent.pageNumber==0}>
                            {/* //if i am in first page then previous will not be shown */}
                              <PaginationLink previous >
                                    previous
                              </PaginationLink>
                            </PaginationItem>
                        {
                          [...Array(postContent.totalPages)].map((item,index)=>(
                            <PaginationItem onClick={()=>changePage(index)} active={index==postContent.pageNumber} key={index}>
                              <PaginationLink >
                                {index+1}
                              </PaginationLink>
                            </PaginationItem>
                          ))
                        }
                            <PaginationItem onClick={()=>changePage(postContent.pageNumber+1)} disabled={postContent.lastPage}>
                              <PaginationLink next>
                                Next
                              </PaginationLink>
                            </PaginationItem>
                          </Pagination>
                        </Container>
       
        </Col>
      </Row>
    </div>
    //<div>hk</div>
  );
}

export default NewFeed;
