import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import InfiniteScroll from "react-infinite-scroll-component";
import { toast } from "react-toastify";
import { Col, Row, Pagination, PaginationItem, PaginationLink, Container } from "reactstrap";
import { loadAllPost } from "../services/post-service";
import Post from "./Post";
import { deletePostService } from "../services/post-service";

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

  const[currentPage, setCurrentPage]=useState(0)

  useEffect(() => {
    //load all post from server

   changePage(currentPage)
  }, [currentPage]);

  const changePage=(pageNumber=0, pageSize=5)=>{
    if(pageNumber > postContent.pageNumber && postContent.lastPage){
      return
    }
    if(pageNumber < postContent.pageNumber && postContent.pageNumber==0){
      return
    }
    loadAllPost(pageNumber,pageSize).then(data=>{
      setPostContent({
        contents:[...postContent.contents,...data.contents],
        totalPages: data.totalPages,
        totalElements: data.totalElements,
        pageSize: data.pageSize,
        lastPage: data.lastPage,
        pageNumber: data.pageNumber})
       
      console.log(data)
      window.scroll(0,0)
    }).catch(error=>{
      toast.error("Error in loaing post")
    })
  }
  const changePageInfinite=()=>{
    console.log("Page changed")
    setCurrentPage(currentPage+1)
  }

  function deletePost(post){
    //deleting post
    deletePostService(post.postId).then(response=>{
      console.log(response)
      toast.success("post is deleted")
   let newPostContents= postContent.contents.filter(p=>p.postId!=post.postId)
    setPostContent({...postContent, contents:newPostContents})
    }).catch(error=>{
      console.log(error)
      toast.error("Error in deleteing post")
    })
  }

  return (
    <div className="container-fluid">
      <Row>
        <Col md={{ size: 12, }}>
          {/* <h1>Blogs count ({postContent?.totalElements})</h1> */}
            <InfiniteScroll
              dataLength={postContent.contents.length}
              next={changePageInfinite}
              hasMore={!postContent.lastPage}

              loader={<h4>Loading...</h4>}
              endMessage={
                <p style={{ textAlign: 'center' }}>
                  <b>Yay! You have seen it all</b>
                </p>}
            >
            {
                            postContent.contents?.map((post) => (
                                <Post deletePost={deletePost}  post={post}  />
                                //key={post.postId}
                            )) 
                        }
            </InfiniteScroll>

                        {/* <Container className="mt-3">
                          <Pagination size="lg">
                            <PaginationItem onClick={()=>changePage(postContent.pageNumber-1)} disabled={postContent.pageNumber==0}>
                            {/* //if i am in first page then previous will not be shown */}
                              {/* <PaginationLink previous >
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
                        </Container> */} 
       
        </Col>
      </Row>
    </div>
    //<div>hk</div>
  );
}

export default NewFeed;
