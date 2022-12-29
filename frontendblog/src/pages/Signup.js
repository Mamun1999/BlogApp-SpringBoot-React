import { useEffect } from "react";
import { useState } from "react";
import { signUp } from "../services/user-services";
import { toast } from "react-toastify";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  Container,
  Form,
  FormGroup,
  Input,
  FormFeedback,
  Label,
  Row,
} from "reactstrap";
import Base from "../components/Base";

const Signup = () => {
  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
    about: "",
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  // useEffect(()=>{
  //   // using use effect, now we will be able to print the data
  //   console.log(data);
  // },[data])

  const submitForm = (event) => {
    event.preventDefault();
    console.log(data);

    // for data validate

    // if (error.isError) {
    //   toast.error("Form data is invalid , correct all details then submit. ");
    //   setError({...error, isError:false})
    //   return;
    // }

    //call server api for sending the data

    signUp(data)
      .then((resp) => {
        console.log(resp);
        //this resp is comming frm (userServices) response.json()
        console.log("success log");
        toast.success("User registered successfully");

        setData({
          name: "",
          email: "",
          password: "",
          about: "",
        });
      })
      .catch((error) => {
        console.log(error);
        console.log("error log");
        setError({
          errors: error,
          isError: true,
        });
      });
  };

  const handleChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
    // setData({...data, name:event.target.value})
    // we can't print data object value because this wroks asychronizely. we have to use useEffect to print adad value
  };

  return (
    <Base>
      <Container>
        {/* ({JSON.stringify(data)}) */}
        <Row className="mt-3">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" inverse>
              <CardHeader>
                <h1 className="text-center">Resgister</h1>
              </CardHeader>

              <CardBody>
                <Form onSubmit={submitForm}>
                  <FormGroup>
                    <Label for="name"> Enter name</Label>
                    <Input
                      type="text"
                      id="name"
                      placeholder="Enter here"
                      onChange={(e) => handleChange(e, "name")}
                      value={data.name}
                      // storing name into e (event) and passing it into handleChange function
                      invalid={
                        error.errors?.response?.data?.name ? true : false
                      }
                    ></Input>

                    <FormFeedback>
                      {error.errors?.response?.data?.name}
                    </FormFeedback>
                  </FormGroup>

                  <FormGroup>
                    <Label for="email"> Enter email</Label>
                    <Input
                      type="email"
                      id="email"
                      placeholder="Enter here"
                      onChange={(e) => handleChange(e, "email")}
                      value={data.email}
                      invalid={
                        error.errors?.response?.data?.email ? true : false
                      }
                    ></Input>
                      <FormFeedback>
                      {error.errors?.response?.data?.email}
                    </FormFeedback>
                  </FormGroup>

                  <FormGroup>
                    <Label for="password"> Enter password</Label>
                    <Input
                      type="password"
                      id="pass"
                      placeholder="Enter here"
                      onChange={(e) => handleChange(e, "password")}
                      value={data.password}
                      invalid={
                        error.errors?.response?.data?.password ? true : false
                      }
                    ></Input>
                      <FormFeedback>
                      {error.errors?.response?.data?.password}
                    </FormFeedback>
                  </FormGroup>

                  <FormGroup>
                    <Label for="about"> About</Label>
                    <Input
                      type="textarea"
                      id="about"
                      style={{ height: "250px" }}
                      placeholder="Enter here"
                      onChange={(e) => handleChange(e, "about")}
                      value={data.about}
                      invalid={
                        error.errors?.response?.data?.about ? true : false
                      }
                    ></Input>
                      <FormFeedback>
                      {error.errors?.response?.data?.about}
                    </FormFeedback>
                  </FormGroup>

                  <Container className="text-center">
                    <Button outline color="info">
                      Register
                    </Button>{" "}
                    <Button
                      outline
                      color="success"
                      type="reset"
                      className="ms-2"
                    >
                      Reset
                    </Button>
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};
export default Signup;
