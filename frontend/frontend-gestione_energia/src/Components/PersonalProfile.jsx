import { Col, Container, Row, Card, Button } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import "./styles/Profile.css";
import { useEffect, useState } from "react";
import { changeName, changePassword } from "../Redux/actions";
import Navbar from "./Navbar.jsx";
import { useNavigate } from "react-router-dom";
import React, { Component } from "react";

const PersonalProfile = () => {
  const token = useSelector((state) => state.token);
  const userData = useSelector((state) => state.data);
  //schermata profilo o password (1 o 2)
  const [option, setOption] = useState(1);
  const [name, setName] = useState(null);
  const [surname, setSurname] = useState(null);
  const [password, setPassword] = useState(null);
  const [confirmPassword, setConfirmPassword] = useState(null);
  const [error, setError] = useState(null);
  const [passwordSecurity, setPasswordSecurity] = useState(null);

  const dispatch = useDispatch();

  const format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;

  const save = (e) => {
    e.preventDefault();

    if (option === 1) {
      if (name !== null || surname !== null) {
        dispatch(changeName(token, { name: name, surname: surname }));
        setError(null);
      } else {
        setError("Entrambe le variabili sono nulle.");
      }
    } else {
      if (
        password === confirmPassword &&
        password !== null &&
        confirmPassword !== null
      ) {
        if (password.trim().length >= 4 && confirmPassword.trim().length >= 4) {
          setError(null);
          dispatch(changePassword(token, password));
        } else {
          setError("La password non può essere inferiore di 4 caratteri");
        }
      } else {
        setError(
          "Assicurati di aver inserito correttamente entrambe le password."
        );
      }
    }
  };

  const getPasswordSecurityStatus = () => {
    return password?.length > 0
      ? passwordSecurity === 1
        ? "La password è ok."
        : passwordSecurity > 1
        ? "La password è sicurissima!"
        : passwordSecurity <= 0
        ? "La password non è sicura."
        : null
      : null;
  };

  const navigate = useNavigate();
  const logOut = () => {
    localStorage.removeItem("token");
    navigate("/home");
    window.location.reload();
  };

  const deleteProfile = () => {
    if (
      !window.confirm(
        "Sei sicuro? eliminando il tuo profilo, ti disiscreverai dal nostro servizio. perderai tutte le azienda salvate con i relativi dati. l'azione non è reversibile."
      )
    ) {
      return;
    } else {
      //elimina profilo
    }
  };

  useEffect(() => {
    let score = password?.length > 6 ? 1 : 0;

    const specialChars = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
    if (specialChars.test(password)) {
      score += 1;
    }

    setPasswordSecurity(score);
  }, [password]);

  return (
    <>
      <Navbar />
      <Container
        fluid
        className="d-flex text-white"
        style={{ backgroundColor: "#1a191c", height: "100%" }}
      >
        {userData !== null ? (
          <Container>
            <Row className="gap-3 pt-5 h-75">
              <Col md={4} className="rounded h-50 column">
                <div className="user-option d-flex flex-column gap-2 ps-3 pt-3">
                  <span className="">Il mio account</span>
                  <small role="button" onClick={() => setOption(1)}>
                    Profilo
                  </small>
                  <small role="button" onClick={() => setOption(2)}>
                    Password
                  </small>
                  <div
                    id="profile-danger-actions"
                    className="d-flex flex-column gap-1"
                  >
                    <small
                      role="button"
                      className="text-warning opacity-50 fw-semibold"
                      onClick={() => logOut()}
                    >
                      LOGOUT
                    </small>
                    <small
                      role="button"
                      className="text-danger opacity-50 fw-semibold"
                      onClick={() => deleteProfile()}
                    >
                      ELIMINA
                    </small>
                  </div>
                </div>
              </Col>
              <Col className="rounded ps-4 pt-3 h-50 column">
                {option === 1 ? (
                  <>
                    {" "}
                    <p>Edit your account information:</p>
                    <div className="change-image-container d-flex gap-4 justify-content-between px-4">
                      <div className="image-name d-flex align-items-center gap-3">
                        <img
                          src={userData.avatar}
                          role="button"
                          alt="avatar"
                          width={40}
                          height={40}
                        />
                        <span>{`${userData.name} ${userData.surname}`}</span>
                      </div>
                      <Button className="px-4" onClick={save}>
                        save
                      </Button>
                    </div>
                    <div className="change-name-container d-flex gap-3 justify-content-evenly pt-5">
                      <input
                        type="text"
                        placeholder="name"
                        className="rounded border-0 py-1 px-2"
                        onChange={(e) => {
                          setName(e.target.value);
                        }}
                      />
                      <input
                        type="text"
                        placeholder="surname"
                        className="rounded border-0 px-2"
                        onChange={(e) => {
                          setSurname(e.target.value);
                        }}
                      />
                    </div>
                  </>
                ) : (
                  <>
                    {" "}
                    <p>Change your password:</p>
                    <div className="change-name-container d-flex flex-column gap-3 justify-content-evenly pt-2 w-75">
                      <input
                        type="password"
                        placeholder="password"
                        className="rounded border-0 py-2 px-2 w-75"
                        onChange={(e) => {
                          setPassword(e.target.value);
                        }}
                      />
                      <small className="opacity-75">
                        {getPasswordSecurityStatus()}
                      </small>
                      <input
                        type="password"
                        placeholder="confirm password"
                        className="rounded border-0 px-2 py-2 w-75"
                        onChange={(e) => {
                          setConfirmPassword(e.target.value);
                        }}
                      />
                      {error ? (
                        <small className="text-warning">{error}</small>
                      ) : null}
                      <Button className="px-4 w-25" onClick={save}>
                        save
                      </Button>
                    </div>
                  </>
                )}
              </Col>
            </Row>
          </Container>
        ) : null}
      </Container>
    </>
  );
};

export default PersonalProfile;
