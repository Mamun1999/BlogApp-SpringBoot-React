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
  Label,
  Row,
} from "reactstrap";
import Base from "../components/Base";

const Signup = () => {
  return (
    <Base>
      <Container>
        <Row className="mt-3">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" inverse>
              <CardHeader><h1 className="text-center">Resgister</h1></CardHeader>

              <CardBody>
                <Form>
                  <FormGroup>
                    <Label for="name"> Enter name</Label>
                    <Input
                      type="text"
                      id="name"
                      placeholder="Enter here"
                    ></Input>
                  </FormGroup>

                  <FormGroup>
                    <Label for="email"> Enter email</Label>
                    <Input
                      type="email"
                      id="email"
                      placeholder="Enter here"
                    ></Input>
                  </FormGroup>

                  <FormGroup>
                    <Label for="password"> Enter password</Label>
                    <Input
                      type="password"
                      id="pass"
                      placeholder="Enter here"
                    ></Input>
                  </FormGroup>

                  <FormGroup>
                    <Label for="about"> About</Label>
                    <Input
                      type="textarea"
                      id="about"
                      style={{ height: "250px" }}
                      placeholder="Enter here"
                    ></Input>
                  </FormGroup>

                  <Container className="text-center">
                    <Button outline color="info">
                      Register
                    </Button>
                    {' '}
                    <Button outline color="success"
                      type="reset"
                      className="ms-2">
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
