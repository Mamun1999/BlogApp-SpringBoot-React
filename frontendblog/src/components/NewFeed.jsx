import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import { Col, Row } from "reactstrap";
import { loadAllPost } from "../services/post-service";
import Post from "./Post";

function NewFeed() {
  const [postContent, setPostContent] = useState({
    contents:[]
  });
  // useEffect(()=>{
  //     //load all post from server
  // },[])

  useEffect(() => {
    //load all post from server

    loadAllPost()
      .then((data) => {
        console.log(data);
        setPostContent(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className="container-fluid">
      <Row>
        <Col md={{ size: 10, offset: 1 }}>
          <h1>Blogs count ({postContent?.totalElements})</h1>
          {
                            postContent.contents?.map((post) => (
                                <Post  post={post}  />
                                //key={post.postId}
                            )) 
                        }
       
        </Col>
      </Row>
    </div>
    //<div>hk</div>
  );
}

export default NewFeed;
