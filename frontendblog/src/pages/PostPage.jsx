import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { Card, CardBody, CardText, Col, Container, Row } from "reactstrap";
import Base from "../components/Base";
import { createComment, loadPost } from "../services/post-service";
import { BASE_URL } from "../services/helper";
import { Button, Input } from "reactstrap";
import { isLoggedIn } from "../Auth";
function PostPage() {
  const { postId } = useParams();
  const [post, setPost] = useState(null);

  const [comment, setComment] = useState({
    content: "",
  });

  useEffect(() => {
    loadPost(postId)
      .then((data) => {
        console.log(data);
        setPost(data);
      })
      .catch((error) => {
        console.log(error);
        toast.error("Error in loading post");
      });
  }, []);

  const printDate = (numbers) => {
    return new Date(numbers).toLocaleDateString();
  };

  const submitComment = () => {
    if(!isLoggedIn()){
        toast.error("You need to login first")
        return
    }
    if(comment.content.trim()==''){
        return
    }
    createComment(comment, post.postId)
      .then((data) => {
        console.log(data);
        toast.success("Comment added")
        setPost({
            ...post,comments:[...post.comments,data.data]
        })
        setComment({
            content:''
        })
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <Base>
      {/* <h1>{postId}</h1> */}

      <Container className="mt-4">
        <Link to="/">Home</Link> / {post && <Link to=""> {post.title}</Link>}
        <Row>
          <Col md={{ size: 12 }}>
            <Card className="mt-3 ps-2">
              {post && (
                <CardBody>
                  <CardText>
                    Posted by <b>{post.user.name}</b> on{" "}
                    <b>{printDate(post.addedDate)} </b>
                  </CardText>
                  <CardText>
                    <span className="text-muted">
                      {post.category.categoryTitle}
                    </span>
                  </CardText>
                  <div
                    className="divider"
                    style={{ width: "100%", height: "1px", background: "cyan" }}
                  ></div>
                  <CardText className="mt-3">
                    <h1>{post.title}</h1>
                  </CardText>

                  <div
                    className="image-container mt-3"
                    style={{ maxWidth: "50%" }}
                  >
                    <img
                      className="img-fluid"
                      src={BASE_URL + "/post/image/" + post.imageName}
                      alt=""
                    ></img>
                  </div>

                  <CardText
                    className="mt-4"
                    dangerouslySetInnerHTML={{ __html: post.content }}
                  ></CardText>
                </CardBody>
              )}
            </Card>
          </Col>
        </Row>
        <Row className="mt-4">
          <Col
            md={{
              size: 9,
              offset: 2,
            }}
          >
            <h3>Comments ({post ? post.comments.length : "0"})</h3>
            {post &&
              post.comments.map((c, index) => (
                <Card className="mt-2 border-0" key={index}>
                  <CardBody>
                    <CardText>{c.content}</CardText>
                  </CardBody>
                </Card>
              ))}

            <Card className="mt-2 border-0">
              <CardBody>
                <Input
                  type="textarea"
                  placeholder="Leave comment"
                  value={comment.content}
                  onChange={(event) =>
                    setComment({ content: event.target.value })
                  }
                ></Input>
                <Button
                  onClick={submitComment}
                  className="mt-2"
                  color="primary"
                >
                  Submit
                </Button>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
}

export default PostPage;
