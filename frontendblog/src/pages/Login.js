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
import { useState } from "react";
import { toast } from "react-toastify";
import { userLogin } from "../services/user-services";
import { doLogin } from "../Auth";
import { useNavigate } from "react-router-dom";
import userContext from "../context/userContext";
import { useContext } from "react";
const Login = () =>{
   
  const userContextData=useContext(userContext)

  const navgate=useNavigate()
  const[loginData, setLoginData]= useState({
    username: "",
    password: ""

  })

  


  const handleChange=(event,inputField)=>{
    let actualValue=event.target.value;

    setLoginData({...loginData,  [inputField]: actualValue});
  
  }

 const submitForm=(event)=>{
    event.preventDefault();

    console.log(loginData)

    if(loginData.username.trim()=='' || loginData.password.trim()==''){
      toast.error("!!Please enter username and password")
      return;
    }

    //call server

    userLogin(loginData).then((data)=>{
      
          console.log(data)

          //save the data to local storage
     
          doLogin(data,()=>{
            console.log("login detailed is saved to local storage");
            // redirect to user dashboard
            userContextData.setUser({
              data: data.user,
              login: true
            })
             navgate("/user/dashboard")
          })
          
          toast.success("Login success!!")

    }).catch(error=>{
         
         console.log(error)
         if(error.response.status==400|| error.response.status==404){
          toast.error(error.response.data.message)
         }
        else{
          toast.error("something went wrongon server!!")
        }
    });



    



  }




    return (
      <Base>
       <Container>
        <Row className="mt-3">
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color="dark" inverse>
              <CardHeader><h1 className="text-center">Login</h1></CardHeader>

              <CardBody>
                <Form onSubmit={submitForm}>
                  

                  <FormGroup>
                    <Label for="email"> Enter email</Label>
                    <Input
                      type="email"
                      id="email"
                      placeholder="Enter here"
                      value={loginData.username}
                      onChange={(e)=> handleChange(e,'username')}
                    ></Input>
                  </FormGroup>

                  <FormGroup>
                    <Label for="password"> Enter password</Label>
                    <Input
                      type="password"
                      id="pass"
                      placeholder="Enter here"
                      value={loginData.password}
                      onChange= {(e)=> handleChange(e,'password')}
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