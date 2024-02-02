/* eslint-disable jsx-a11y/anchor-is-valid */
import { useSelector } from "react-redux";
import hero from "../assets/hero.webp";
import heroUnlogged from "../assets/hero-unlogged.webp";
import { useNavigate } from "react-router";

const HeroSection = () => {
  const navigate = useNavigate();

  const userData = useSelector((state) => state.data);
  const isLogged = userData !== null;
  let name;
  let surname;

  if (isLogged) {
    name = userData.name;
    surname = userData.surname;
  }
  return (
    <div
      className="text-center bg-image"
      style={{
        backgroundImage: `url(${isLogged ? hero : heroUnlogged})`,
        height: 600,
        backgroundSize: "cover",
        backgroundPosition: "center"
      }}
    >
      <div
        className="mask h-100 w-100"
        style={{ backgroundColor: "rgba(0, 0, 0, 0.7)" }}
      >
        <div className="d-flex justify-content-center align-items-center h-100 w-100">
          <div
            className="text-white"
            style={{ textShadow: "0px 0px 5px #000000" }}
          >
            <h1 className="mb-3">
              {isLogged ? `Bentornato ${name}` : `Benvenuto!`}
            </h1>
            <h4 className="mb-3">
              {isLogged
                ? `Vuoi visualizzare un resoconto personale?`
                : `Unisciti a noi e proiettiamoci insieme verso il futuro`}
            </h4>
            <button
              className="btn btn-outline-light btn-lg border border-0 border-bottom"
              style={{
                backgroundColor: "rgba(64, 68, 237, 0.2)",
                boxShadow: "-1px 1px 11px 1px #000000",
                textShadow: "0px 0px 3px #000000"
              }}
              onClick={() =>
                !isLogged ? navigate("/register") : navigate("/dashboard")
              }
            >
              {isLogged ? `Apri Dashboard` : `Registrati ora`}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HeroSection;
