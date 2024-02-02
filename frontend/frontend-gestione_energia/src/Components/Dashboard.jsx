import { Col, Container, Row, Card, Dropdown } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import "./styles/Dashboard.css";
import { useEffect } from "react";
import { fetchBillsFromClient, fetchClientsData } from "../Redux/actions";

const Dashboard = () => {
  const userData = useSelector((state) => state.data);
  const token = useSelector((state) => state.token);
  const clientsData = useSelector((state) => state.clientsData);
  const billsData = useSelector((state) => state.billsData);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchClientsData(token)).then((clientInfo) => {
      dispatch(fetchBillsFromClient(token, clientInfo.content[0].piva));
    });
  }, []);

  let clientInfo;
  if (clientsData) {
    if (clientsData.content.length > 0) {
      clientInfo = clientsData.content[0];
    }
  }

  return (
    <Container
      fluid
      className="d-flex align-items-center"
      style={{ backgroundColor: "#1a191c", height: "100%" }}
    >
      {userData !== null &&
      clientsData?.content.length > 0 &&
      billsData?.content.length > 0 ? (
        <Container className="h-75">
          <Row className="flex-column gap-3">
            <Col>
              <Card className="ps-2 py-2">
                {" "}
                <Card.Body>
                  <Card.Title className="h5 pb-1">
                    Benvenuto {userData.name}!
                  </Card.Title>
                  <Card.Text className="small opacity-75 d-flex gap-4">
                    Ecco il riepilogo della tua azienda {clientInfo.name}.
                    <Dropdown className="z-3">
                      <Dropdown.Toggle variant="success" id="dropdown-basic">
                        Dropdown Button
                      </Dropdown.Toggle>

                      <Dropdown.Menu>
                        <Dropdown.Item href="#/action-1">Action</Dropdown.Item>
                        <Dropdown.Item href="#/action-2">
                          Another action
                        </Dropdown.Item>
                        <Dropdown.Item href="#/action-3">
                          Something else
                        </Dropdown.Item>
                      </Dropdown.Menu>
                    </Dropdown>
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
                    <Card.Text className="d-flex flex-column gap-2">
                      <span className="opacity-75 text-truncate">
                        Partita iva: {clientInfo.piva}
                      </span>
                      <span className="opacity-75">{`Tipo: ${clientInfo.ctype}`}</span>
                      <span className="opacity-75">{`Data inserimento: ${clientInfo.insertDate}`}</span>
                      <span className="opacity-75">{`Data ultimo contatto: ${clientInfo.lastCall}`}</span>
                    </Card.Text>
                  </Card.Body>
                </Card>
              </Col>
              <Col>
                <Card className="h-100">
                  <Card.Body>
                    <Card.Title>Contatti e Comunicazione</Card.Title>
                    <Card.Text className="d-flex flex-column gap-2">
                      <span className="opacity-75">{`Email: ${clientInfo.email}`}</span>
                      <span className="opacity-75">{`PEC: ${clientInfo.pec}`}</span>
                      <span className="opacity-75">{`numero telefonico: ${clientInfo.number}`}</span>
                    </Card.Text>
                  </Card.Body>
                </Card>
              </Col>
            </div>
            <div className="d-flex gap-2">
              <Col>
                <Card className="h-100">
                  <Card.Body>
                    <Card.Title>Informazioni finanziarie</Card.Title>
                    <Card.Text className="d-flex flex-column gap-2">
                      <span className="opacity-75">{`Fatturato annuo: ${clientInfo.annualTurnOver}€`}</span>
                    </Card.Text>
                  </Card.Body>
                </Card>
              </Col>
              <Col className="g-2">
                <Card className="h-100">
                  <Card.Body>
                    <Card.Title>Fatture e pagamenti</Card.Title>
                    <Card.Text>
                      <Row className="mb-3">
                        <Col>
                          <span>Data:</span>
                        </Col>
                        <Col>
                          <span>Totale:</span>
                        </Col>
                        <Col md={5}>
                          <span>Numero fattura:</span>
                        </Col>
                      </Row>

                      {billsData.content.map((bill, index) => {
                        return (
                          <Row>
                            <Col className="border border-0 border-end">
                              <span key={index}>{bill.date}</span>
                            </Col>
                            <Col className="border border-0 border-end">
                              <span key={index}>{bill.total}€</span>
                            </Col>
                            <Col md={5} className="text-center">
                              <span key={index}>{bill.billNumber}</span>
                            </Col>
                          </Row>
                        );
                      })}
                    </Card.Text>
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
