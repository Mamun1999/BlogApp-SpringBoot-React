import { useState } from "react";
import { useEffect } from "react";
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
const AddPost = () => {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    loadAllCategories()
      .then((data) => {
        console.log(data);
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className="wrapper">
      <Card className="shadow-sm  border-0 mt-2">
        <CardBody>
          <h3>What is going in your mind</h3>

          <Form>
            <div className="my-3">
              <Label for="Title"> Post title</Label>
              <Input
                type="text"
                id="title"
                placeholder="Enter here"
                className="rounded-0"
              />
            </div>

            <div className="my-3">
              <Label for="content">Post Content</Label>
              <Input
                type="textarea"
                id="content"
                placeholder="Enter here"
                className="rounded-0"
                style={{ height: "300px" }}
              />
            </div>

            <div className="my-3">
              <Label for="category">Post Category</Label>
              <Input
                type="select"
                id="category"
                placeholder="Enter here"
                className="rounded-0"
              >
                <option disabled value={0}>
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
