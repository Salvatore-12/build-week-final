import { Fragment, useEffect } from "react";
import { Card, Col, Container, Row } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchAllClients,
  fetchAllUsers,
  fetchUserRole,
  setAllUsers
} from "../Redux/actions";
import { useNavigate } from "react-router-dom";

const BackOffice = () => {
  const token = useSelector((state) => state.token);
  const allUsers = useSelector((state) => state.allUsers);
  const isUserAdmin =
    useSelector((state) => state.role) === "ADMIN" ? true : false;
  const dispatch = useDispatch();

  const allClients = useSelector((state) => state.allClients);

  const navigate = useNavigate();

  useEffect(() => {
    dispatch(fetchUserRole(token)).then((role) => {
      if (role === "ADMIN") {
        alert("ciao");
        dispatch(fetchAllUsers(token));
        dispatch(fetchAllClients(token));
      }
    });
  }, []);

  return (
    <>
      {isUserAdmin ? (
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
                    <Card.Title className="h5 pb-1">
                      Pagina per amministratori.
                    </Card.Title>
                    <Card.Text className="small opacity-75 d-flex gap-4"></Card.Text>
                  </Card.Body>
                </Card>
              </Col>
              <div className="d-flex gap-2 flex-wrap">
                <Col>
                  {" "}
                  <Card>
                    <Card.Body>
                      <Card.Title>Lista utenti</Card.Title>
                      <Card.Text className="d-flex flex-column gap-2">
                        <Row>
                          <Col className="d-flex fw-bold">
                            <Col md={3}>
                              <span className="fw-bold h4">Nome</span>
                            </Col>
                            <Col md={3}>
                              <span className="fw-bold h4">Cognome</span>
                            </Col>
                            <Col md={3}>
                              <span className="fw-bold h4">Email</span>
                            </Col>
                            <Col md={3}>
                              <span className="fw-bold h4">Username</span>
                            </Col>
                          </Col>
                        </Row>
                        <Row className="flex-column gap-3">
                          {allUsers?.content.map((user, index) => {
                            return (
                              <>
                                {" "}
                                <Col className="d-flex">
                                  <Col md={3}>{user.name}</Col>
                                  <Col md={3}>{user.surname}</Col>
                                  <Col md={3}>{user.email}</Col>
                                  <Col md={3}>{user.username}</Col>
                                </Col>
                              </>
                            );
                          })}
                        </Row>
                      </Card.Text>
                    </Card.Body>
                  </Card>
                </Col>
              </div>
              <div className="d-flex gap-2 flex-wrap">
                <Col>
                  {" "}
                  <Card>
                    <Card.Body>
                      <Card.Title>Lista Clients</Card.Title>
                      <Card.Text className="d-flex flex-column gap-2">
                        <Row>
                          <Col className="d-flex fw-bold">
                            <Col md={3}>
                              <span className="fw-bold h4">P.IVA</span>
                            </Col>
                            <Col md={3}>
                              <span className="fw-bold h4">Nome</span>
                            </Col>
                            <Col md={3}>
                              <span className="fw-bold h4">PEC</span>
                            </Col>
                            <Col md={3}>
                              <span className="fw-bold h4">
                                Numero di telefono
                              </span>
                            </Col>
                          </Col>
                        </Row>
                        <Row className="flex-column gap-3">
                          {allClients?.content.map((client, index) => {
                            return (
                              <Fragment key={index}>
                                {" "}
                                <Col className="d-flex">
                                  <Col md={3} className="text-truncate">
                                    {client.piva}
                                  </Col>
                                  <Col md={3}>{client.name}</Col>
                                  <Col md={3}>{client.pec}</Col>
                                  <Col md={3}>{client.number}</Col>
                                </Col>
                              </Fragment>
                            );
                          })}
                        </Row>
                      </Card.Text>
                    </Card.Body>
                  </Card>
                </Col>
              </div>
            </Row>
          </Container>
        </Container>
      ) : (
        <h1>Hai finito i soldi. vai a casa.</h1>
      )}
    </>
  );
};

export default BackOffice;
