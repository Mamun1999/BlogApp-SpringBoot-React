import Base from "../components/Base";
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

const Login = () =>{

    return (
      <Base>
       <Container>
        <Row className="mt-3">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" inverse>
              <CardHeader><h1 className="text-center">Login</h1></CardHeader>

              <CardBody>
                <Form>
                  

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

                 

                  <Container className="text-center">
                    <Button outline color="info">
                      Register
                    </Button>
                    {' '}
                   
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
      </Base>
    );
}
export default Login;