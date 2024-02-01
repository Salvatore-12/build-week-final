import { Col, Container, Row } from "react-bootstrap";
import "./styles/Home.css";
import { useDispatch, useSelector } from "react-redux";
import NavBar from "./Navbar";
import { useEffect } from "react";
import { fetchUserData, setUserData } from "../Redux/actions";
import HeroSection from "./HeroSection";
import ShowCaseCard from "./ShowCaseCard";

const Home = () => {
  const token = useSelector((state) => state.token);
  const userData = useSelector((state) => state.data);
  const dispatch = useDispatch();

  return (
    <>
      <NavBar />
      <Container
        fluid
        className="home-container overflow-auto d-flex flex-column flex-grow-1"
        style={{ backgroundColor: "#27262c" }}
      >
        <Row className=" flex-row flex-grow-1 h-100">
          <Col md={12} className="px-0">
            <HeroSection />
          </Col>
          <Col className="d-flex flex-column justify-content-start gap-4">
            <h1 className="text-white text-truncate text-center">
              Perchè dovresti scegliere noi?
            </h1>
            <div className="d-flex flex-wrap justify-content-center gap-3">
              <ShowCaseCard
                title="Sostenibilità"
                text="Ci teniamo alla sostenibilità, per questo i nostri lavoratori vengono pagati 2500 euro al secondo."
              />
              <ShowCaseCard
                title="Fonti rinnovabili"
                text="Ci teniamo all'ambiente, per questo forniamo energia prodotta da fonti 100% rinnovabili. Il nostro obiettivo è arrivare a 0 emissioni entro domani"
              />
              <ShowCaseCard
                title="Fiducia"
                text="PowerNet è nata nel 1980 ed è diventata velocemente il punto di riferimento nel campo energetico. Milioni si fidano di noi."
              />
            </div>
          </Col>
        </Row>
      </Container>{" "}
    </>
  );
};

export default Home;
