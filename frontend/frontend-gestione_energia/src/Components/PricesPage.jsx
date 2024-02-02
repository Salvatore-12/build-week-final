import { Card, Col, Container, Row } from "react-bootstrap";
import NavBar from "./Navbar";

const PricesPage = () => {
  return (
    <>
      <NavBar />
      <Container
        fluid
        className="d-flex pt-5"
        style={{ backgroundColor: "#1a191c", height: "100%" }}
      >
        <Container>
          <Row>
            <Col>
              <Card
                style={{ backgroundColor: "#0f101c", borderColor: "#4044ed" }}
              >
                <Card.Body>
                  <Card.Title className="text-white">
                    Trova il piano adatto a te
                  </Card.Title>
                  <Card.Text className="text-white opacity-50">
                    Abbiamo una soluzione adatta a tutte le esigenze:
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
          </Row>
          <Row className="pt-5 h-75">
            <Col md={4} style={{ alignSelf: "stretch" }}>
              {" "}
              <Card
                style={{
                  backgroundColor: "#0f101c",
                  borderColor: "#4044ed",
                  height: "100%"
                }}
              >
                <Card.Body className="px-5">
                  <Card.Title className="text-white fw-bold display-6 text-center">
                    Free
                  </Card.Title>
                  <Card.Text className="text-white opacity-50 d-flex flex-column gap-5 text-center">
                    certo, e noi siamo stupidi che ti regaliamo le cose?
                    <ul className="text-start d-flex flex-column gap-2">
                      <li style={{ color: "#9597fc" }}>trova un lavoro</li>
                      <li style={{ color: "#9597fc" }}>prendi lo stipendio</li>
                      <li style={{ color: "#9597fc" }}>torna qui e paga</li>
                    </ul>
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <Col md={4}>
              <Card
                style={{
                  backgroundColor: "#0f101c",
                  borderColor: "#4044ed",
                  height: "100%"
                }}
              >
                <Card.Body>
                  <Card.Title className="text-white fw-bold display-6 text-center">
                    600EURO/S
                  </Card.Title>
                  <Card.Text className="text-white opacity-50 d-flex flex-column gap-5 text-center">
                    L'energia costa, stacce.
                    <ul className="text-start d-flex flex-column gap-2">
                      <li style={{ color: "#9597fc" }}>prendi lo stipendio</li>
                      <li style={{ color: "#9597fc" }}>buttalo con noi</li>
                      <li style={{ color: "#9597fc" }}>
                        ottieni gas e luce a casa
                      </li>
                      <li style={{ color: "#9597fc" }}>
                        non hai più soldi per mangiare
                      </li>
                      <li style={{ color: "#9597fc" }}>almeno hai il wifi</li>
                      <li style={{ color: "#9597fc" }}>niente rimborsi</li>
                    </ul>
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <Col md={4}>
              <Card
                style={{
                  backgroundColor: "#0f101c",
                  borderColor: "#4044ed",
                  height: "100%"
                }}
              >
                <Card.Body>
                  <Card.Title className="text-white">
                    Trova il piano adatto a te
                  </Card.Title>
                  <Card.Text className="text-white opacity-50">
                    Abbiamo una soluzione adatta a tutte le esigenze:
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </Container>
    </>
  );
};

export default PricesPage;
