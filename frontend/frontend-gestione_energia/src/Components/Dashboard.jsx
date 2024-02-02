import { Col, Container, Row, Card, Dropdown } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import "./styles/Dashboard.css";
import { useEffect, useState } from "react";
import { fetchBillsFromClient, fetchClientsData } from "../Redux/actions";

const Dashboard = () => {
  const userData = useSelector((state) => state.data);
  const token = useSelector((state) => state.token);
  const clientsData = useSelector((state) => state.clientsData);
  const billsData = useSelector((state) => state.billsData);

  const [clientSelected, setClientSelected] = useState(null);
  const [billSelected, setBillSelected] = useState(null);

  const formatter = new Intl.NumberFormat("it-IT", {
    style: "currency",
    currency: "EUR"
  });

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchClientsData(token)).then((clientInfo) => {
      if (clientInfo) {
        setClientSelected(clientInfo.content[0]);
      }
      dispatch(fetchBillsFromClient(token, clientInfo?.content[0].piva)).then(
        (bills) => {
          if (bills) {
            setBillSelected(bills.content[0]);
          }
        }
      );
    });
  }, []);

  let clientInfo;
  if (clientsData) {
    if (clientsData.content.length > 0) {
      clientInfo = clientsData.content[0];
    }
  }

  useEffect(() => {
    dispatch(fetchBillsFromClient(token, clientSelected?.piva));
  }, [clientSelected]);

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
            <Dropdown>
              <Dropdown.Toggle
                id="dropdown-basic"
                style={{ backgroundColor: "#4044ED", borderColor: "#4044ED" }}
              >
                {clientSelected?.name}
              </Dropdown.Toggle>

              <Dropdown.Menu>
                {clientsData.content.map((client, index) => {
                  return (
                    <Dropdown.Item
                      key={index}
                      onClick={() => setClientSelected(client)}
                    >
                      {client.name}
                    </Dropdown.Item>
                  );
                })}
              </Dropdown.Menu>
            </Dropdown>
            <Col>
              <Card className="ps-2 py-2">
                {" "}
                <Card.Body>
                  <Card.Title className="h5 pb-1">
                    Benvenuto {userData.name}!
                  </Card.Title>
                  <Card.Text className="small opacity-75 d-flex gap-4">
                    Ecco il riepilogo della tua azienda {clientSelected.name}.
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
                        Partita iva: {clientSelected.piva}
                      </span>
                      <span className="opacity-75">{`Tipo: ${clientSelected.ctype}`}</span>
                      <span className="opacity-75">{`Data inserimento: ${clientSelected.insertDate}`}</span>
                      <span className="opacity-75">{`Data ultimo contatto: ${clientSelected.lastCall}`}</span>
                    </Card.Text>
                  </Card.Body>
                </Card>
              </Col>
              <Col>
                <Card className="h-100">
                  <Card.Body>
                    <Card.Title>Contatti e Comunicazione</Card.Title>
                    <Card.Text className="d-flex flex-column gap-2">
                      <span className="opacity-75">{`Email: ${clientSelected?.email}`}</span>
                      <span className="opacity-75">{`PEC: ${clientSelected?.pec}`}</span>
                      <span className="opacity-75">{`numero telefonico: ${clientSelected?.number}`}</span>
                    </Card.Text>
                  </Card.Body>
                </Card>
              </Col>
            </div>
            <div className="d-flex gap-2">
              <Col md={4}>
                <Card className="h-100">
                  <Card.Body>
                    <Card.Title>Informazioni finanziarie</Card.Title>
                    <Card.Text className="d-flex flex-column gap-2">
                      <span className="opacity-75">{`Fatturato annuo: ${formatter.format(
                        clientSelected.annualTurnOver
                      )}`}</span>
                    </Card.Text>
                  </Card.Body>
                </Card>
              </Col>
              <Col md={5} className="g-2">
                <Card className="h-100">
                  <Card.Body>
                    <div className="d-flex gap-3">
                      <Card.Title>Fatture e pagamenti</Card.Title>
                      <Dropdown>
                        <Dropdown.Toggle
                          id="dropdown-basic"
                          className="px-2 py-0"
                          style={{
                            backgroundColor: "transparent",
                            borderColor: "#4044ED"
                          }}
                        ></Dropdown.Toggle>

                        <Dropdown.Menu>
                          {billsData.content.map((bill, index) => {
                            return (
                              <Dropdown.Item
                                key={index}
                                onClick={() => setBillSelected(bill)}
                              >
                                {bill.date}
                              </Dropdown.Item>
                            );
                          })}
                        </Dropdown.Menu>
                      </Dropdown>
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
                        <Col md={3} className="border border-0 border-end">
                          <span>{billSelected?.date}</span>
                        </Col>
                        <Col
                          md={4}
                          className="border border-0 border-end text-end"
                        >
                          <span>{formatter.format(billSelected?.total)}</span>
                        </Col>
                        <Col md={5} className="text-center">
                          <span>{billSelected?.billNumber}</span>
                        </Col>
                      </Row>
                    </Card.Text>
                  </Card.Body>
                </Card>
              </Col>
              <Col className="g-2">
                <Card className="h-100">
                  <Card.Body>
                    <Card.Title>Performance e benchmark</Card.Title>
                    <Card.Text className="opacity-75">
                      Work in progress
                    </Card.Text>
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
