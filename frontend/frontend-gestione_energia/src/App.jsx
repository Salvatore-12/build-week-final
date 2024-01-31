import { Provider } from "react-redux";
import store from "./Redux/store";
import "bootstrap/dist/css/bootstrap.css";
import { Col, Row } from "react-bootstrap";
import LoginPage from "./Components/LoginPage";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    // <Provider store={store}>
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </BrowserRouter>
    // </Provider>
  );
}

export default App;
