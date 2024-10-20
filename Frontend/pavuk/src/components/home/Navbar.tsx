import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
    return (
      <header>
        <nav className="navbar-container px-24 py-5">
          <Link className="-mt-2 mr-10 text-3xl" to="/">Pavuk</Link>
          <div className="navbar-link-container">
            <Link className="navbar-link px-1 text-lg" to="/">
              Головна
            </Link>
          </div>
          <ul className="navbar-list">
            <li className="mr-10 text-lg">UA</li>

            <button className="navbar-list-button px-7 py-2 text-lg">
              Вхід
            </button>
          </ul>
        </nav>
      </header>
    );
  }
  
  export default Navbar;