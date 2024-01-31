import { Col, Container, Row } from "react-bootstrap";
import "./styles/Home.css";

const Home = () => {
  return (
    <Container
      fluid
      className="home-container"
      style={{ backgroundColor: "#27262c" }}
    >
      <Row>
        <Col>
          <h1 className="text-white">Ci stiamo lavorando...</h1>
        </Col>
      </Row>
    </Container>
  );
};

export default Home;
