import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import "./styles/NavBar.css";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

const NavBar = () => {
  const navigate = useNavigate();
  const userData = useSelector((state) => state.data);

  return (
    <Navbar expand="lg" style={{ backgroundColor: "#4044ED" }}>
      <Container fluid className="mx-3">
        <Navbar.Brand href="#home" className="text-white">
          Enel, Carbone oltre le persone
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link
              className="text-white"
              onClick={() => {
                navigate("/home");
              }}
            >
              Home
            </Nav.Link>
            <Nav.Link href="#link" className="text-white">
              Prices
            </Nav.Link>
          </Nav>
          <Nav>
            {userData === null ? (
              <>
                <Nav.Link
                  href="#"
                  className="text-white"
                  onClick={() => {
                    navigate("/login");
                  }}
                >
                  Login
                </Nav.Link>
                <Nav.Link
                  eventKey={2}
                  href="#"
                  className="text-white"
                  onClick={() => {
                    navigate("/register");
                  }}
                >
                  Register
                </Nav.Link>
              </>
            ) : (
              <div>
                <img
                  src={userData.avatar}
                  alt="avatar"
                  width={30}
                  height={30}
                  className="rounded"
                />
              </div>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavBar;
