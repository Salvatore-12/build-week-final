import { Col, Container, Row, Card } from "react-bootstrap";
import { useSelector } from "react-redux";
import "./styles/Dashboard.css";

const Dashboard = () => {
  const userData = useSelector((state) => state.data);
  return (
    <Container
      fluid
      className="d-flex align-items-center"
      style={{ backgroundColor: "#1a191c", height: "100%" }}
    >
      {userData !== null ? (
        <Container className="h-75">
          <Row className="flex-column gap-3">
            <Col>
              <Card className="ps-2 py-2">
                <Card.Body>
                  <Card.Title className="h5 pb-1">
                    Benvenuto {userData.name}!
                  </Card.Title>
                  <Card.Text className="small opacity-75">
                    Ecco il riepilogo della tua azienda [Nome Azienda].
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <div className="d-flex gap-2 flex-wrap">
              <Col md={4}>
                {" "}
                <Card>
                  <Card.Body>
                    <Card.Title>Informazioni Principali</Card.Title>
                    {/* Aggiungi qui le informazioni principali */}
                  </Card.Body>
                </Card>
              </Col>
              <Col>
                <Card>
                  <Card.Body>
                    <Card.Title>Contatti e Comunicazione</Card.Title>
                    {/* Aggiungi qui i contatti e la comunicazione */}
                  </Card.Body>
                </Card>
              </Col>
            </div>
            <div className="d-flex gap-2">
              <Col>
                <Card>
                  <Card.Body>
                    <Card.Title>Informazioni Finanziarie</Card.Title>
                  </Card.Body>
                </Card>
              </Col>
              <Col className="g-2">
                <Card>
                  <Card.Body>
                    <Card.Title>Fatture e Pagamenti</Card.Title>
                  </Card.Body>
                </Card>
              </Col>
              <Col className="g-2">
                <Card>
                  <Card.Body>
                    <Card.Title>Performance e benchmark</Card.Title>
                  </Card.Body>
                </Card>
              </Col>
            </div>
          </Row>
        </Container>
      ) : null}
    </Container>
  );
};

export default Dashboard;
