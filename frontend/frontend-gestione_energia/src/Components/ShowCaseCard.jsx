import { Row, Col, Card } from "react-bootstrap";

const ShowCaseCard = ({ title, text }) => {
  return (
    <Col
      style={{
        flexBasis: "290px",
        flexGrow: "0.3",
        flexShrink: "1",
        alignSelf: "stretch",
        display: "flex"
      }}
    >
      <Card style={{ backgroundColor: "#0f101c", borderColor: "#4044ed" }}>
        <Card.Body>
          <Card.Title className="text-white">{title}</Card.Title>
          <Card.Text className="text-white opacity-50">{text}</Card.Text>
        </Card.Body>
      </Card>
    </Col>
  );
};

export default ShowCaseCard;
