import React from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./styles/Login.css";

const Login = () => {
  return (
    <Container
      fluid
      className="login-container"
      style={{ backgroundColor: "#27262c" }}
    >
      <Row className="justify-content-center align-items-center h-100">
        <Col md={4}>
          <Form className="login-form d-flex flex-column gap-5">
            <h1 className="login-title text-start mb-4">login</h1>
            <Form.Group controlId="formBasicEmail">
              <Form.Control
                type="text"
                placeholder="Username"
                className="border-0 border-bottom rounded-0 shadow-none text-white"
                style={{ fontSize: "1.2rem" }}
              />
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
              <Form.Control
                type="password"
                placeholder="Password"
                className="border-0 border-bottom rounded-0 shadow-none text-white"
                style={{ fontSize: "1.2rem" }}
              />
            </Form.Group>

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
