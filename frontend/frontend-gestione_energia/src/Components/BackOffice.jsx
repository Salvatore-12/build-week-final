import { Card, Col, Container, Row } from "react-bootstrap";

const BackOffice = () => {
  return (
    <Container
      fluid
      className="d-flex align-items-center"
      style={{ backgroundColor: "#1a191c", height: "100%" }}
    >
      <Container className="h-75">
        <Row className="flex-column gap-3">
          <Col>
            <Card className="ps-2 py-2">
              {" "}
              <Card.Body>
                <Card.Title className="h5 pb-1"></Card.Title>
                <Card.Text className="small opacity-75 d-flex gap-4"></Card.Text>
              </Card.Body>
            </Card>
          </Col>
          <div className="d-flex gap-2 flex-wrap">
            <Col md={4}>
              {" "}
              <Card>
                <Card.Body>
                  <Card.Title>Informazioni Principali</Card.Title>
                  <Card.Text className="d-flex flex-column gap-2"></Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <Col>
              <Card className="h-100">
                <Card.Body>
                  <Card.Title>Contatti e Comunicazione</Card.Title>
                  <Card.Text className="d-flex flex-column gap-2"></Card.Text>
                </Card.Body>
              </Card>
            </Col>
          </div>
          <div className="d-flex gap-2">
            <Col md={4}>
              <Card className="h-100">
                <Card.Body>
                  <Card.Title>Informazioni finanziarie</Card.Title>
                  <Card.Text className="d-flex flex-column gap-2"></Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <Col md={5} className="g-2">
              <Card className="h-100">
                <Card.Body>
                  <div className="d-flex gap-3">
                    <Card.Title>Fatture e pagamenti</Card.Title>
                  </div>
                  <Card.Text>
                    <Row className="mb-3">
                      <Col md={3}>
                        <span>Data:</span>
                      </Col>
                      <Col md={4}>
                        <span>Totale:</span>
                      </Col>
                      <Col md={5}>
                        <span>Numero fattura:</span>
                      </Col>
                    </Row>

                    <Row>
                      <Col md={3} className="border border-0 border-end"></Col>
                      <Col
                        md={4}
                        className="border border-0 border-end text-end"
                      ></Col>
                      <Col md={5} className="text-center"></Col>
                    </Row>
                  </Card.Text>
                </Card.Body>
              </Card>
            </Col>
            <Col className="g-2">
              <Card className="h-100">
                <Card.Body>
                  <Card.Title>Performance e benchmark</Card.Title>
                  <Card.Text className="opacity-75">Work in progress</Card.Text>
                </Card.Body>
              </Card>
            </Col>
          </div>
        </Row>
      </Container>
    </Container>
  );
};

export default BackOffice;
