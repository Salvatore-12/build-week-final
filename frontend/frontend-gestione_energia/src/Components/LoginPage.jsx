import React from "react";
import { Container, Row, Col, Form, Button, InputGroup } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import "./styles/Login.css";

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleShowPassword = (e) => {
    e.preventDefault();
    setShowPassword(!showPassword);
  };

  const baseEndpoint = "http://localhost:3000/auth/login";

  const login = async () => {
    const response = await fetch(baseEndpoint, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        email: email,
        password: password
      })
    });
  };

  return (
    <Container
      fluid
      className="login-container"
      style={{ backgroundColor: "#27262c" }}
    >
      <Row className="justify-content-center align-items-center h-100">
        <Col md={4}>
          <Form
            className="login-form d-flex flex-column gap-5"
            onSubmit={login}
          >
            <h1 className="login-title text-start mb-4">login</h1>
            <Form.Group controlId="formBasicEmail">
              <Form.Control
                type="text"
                placeholder="Email"
                className="border-0 border-bottom rounded-0 shadow-none text-white"
                style={{ fontSize: "1.2rem" }}
                onChange={(e) => setEmail(e.target.value)}
              />
            </Form.Group>

            <InputGroup controlId="formBasicPassword">
              <Form.Control
                type={showPassword ? "text" : "password"}
                placeholder="Password"
                className="border-0 border-bottom rounded-0 shadow-none text-white"
                style={{ fontSize: "1.2rem" }}
                onChange={(e) => setPassword(e.target.value)}
              />
              <InputGroup.Text onClick={handleShowPassword} type="button">
                {showPassword ? "Nascondi" : "Mostra"}
              </InputGroup.Text>
            </InputGroup>

            <div className="d-grid gap-2">
              <button
                className="login-submit text-center"
                size="lg"
                type="submit"
              >
                login
              </button>
            </div>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default Login;
