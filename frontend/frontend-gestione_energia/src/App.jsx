import { Provider } from "react-redux";
import store from "./Redux/store";
import "bootstrap/dist/css/bootstrap.css";
import { Col, Container, Row } from "react-bootstrap";
import LoginPage from "./Components/LoginPage";
import RegisterPage from "./Components/RegisterPage";
import Home from "./Components/Home";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import NavBar from "./Components/Navbar";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/home" element={<Home />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
