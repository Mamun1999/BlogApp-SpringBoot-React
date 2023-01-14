
import { Col, Row,Container } from "reactstrap";
import Base from "../components/Base";
import CategorySideMenu from "../components/CategorySideMenu";
import NewFeed from "../components/NewFeed";

const Home = () =>{


  
    
    return (

        
       <Base>
         <Container className="pt-3">
         <Row>
          <Col md={2} className="border">
            <CategorySideMenu/>

          </Col>

          <Col md={10}>
          <NewFeed/>
          </Col>
         </Row>

         </Container>
          
       </Base>
    );
}
export default Home;