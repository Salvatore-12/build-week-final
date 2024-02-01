import { Col, Container, Row } from "react-bootstrap";
import "./styles/Home.css";
import { useDispatch, useSelector } from "react-redux";
import NavBar from "./Navbar";
import { useEffect } from "react";
import { fetchUserData, setUserData } from "../Redux/actions";

const Home = () => {
  const token = useSelector((state) => state.token);
  const userData = useSelector((state) => state.data);
  const dispatch = useDispatch();

  return (
    <>
      <NavBar />
      <Container
        fluid
        className="home-container overflow-hidden d-flex flex-column flex-grow-1"
        style={{ backgroundColor: "#27262c" }}
      >
        <Row className="pt-5 align-items-center mx-3 flex-column flex-grow-1 h-100">
          <Col md={12}>
            <h1 className="text-white text-truncate">
              {token === null || userData === null
                ? "Registrati o accedi"
                : `ciao ${userData.name} ${userData.surname} (${userData.username})`}
            </h1>
          </Col>
        </Row>
      </Container>{" "}
    </>
  );
};

export default Home;
