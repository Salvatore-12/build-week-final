import { Provider } from "react-redux";
import store from "./Redux/store";
import "bootstrap/dist/css/bootstrap.css";
import { Col, Container, Row } from "react-bootstrap";
import LoginPage from "./Components/LoginPage";
import RegisterPage from "./Components/RegisterPage";
import Home from "./Components/Home";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import NavBar from "./Components/Navbar";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchUserData, setLoading } from "./Redux/actions";
import OverlayLoading from "./Components/OverlayLoading";
import Dashboard from "./Components/Dashboard.jsx";
import PersonalProfile from "./Components/PersonalProfile.jsx";

function App() {
  const token = useSelector((state) => state.token);
  const dispatch = useDispatch();

  useEffect(() => {
    if (token !== null) {
      dispatch(fetchUserData(token)).then((data) => {
        if (data) {
          dispatch(setLoading(false));
        }
      });
    } else {
      dispatch(setLoading(false));
    }
  }, []);

  return (
    <BrowserRouter>
      <OverlayLoading />
      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/me" element={<PersonalProfile />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/home" element={<Home />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
