import Spinner from "react-bootstrap/Spinner";
import Container from "react-bootstrap/Container";
import { useSelector } from "react-redux";
import "./styles/OverlayLoading.css";

const OverlayLoading = function () {
  const isLoading = useSelector((state) => state.loading);
  return (
    <Container
      fluid
      style={{ zIndex: "1000" }}
      className={
        isLoading
          ? "h-100 position-fixed z-3 bg-dark d-flex align-items-center justify-content-center"
          : "h-100 position-fixed z-3 bg-dark d-flex align-items-center justify-content-center fade-out"
      }
    >
      {" "}
      <Spinner animation="border" role="status" variant="primary">
        <span className="visually-hidden">Loading...</span>
      </Spinner>
    </Container>
  );
};

export default OverlayLoading;
