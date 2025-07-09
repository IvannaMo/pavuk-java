import { Link } from "react-router-dom";
import "./Footer.css";
 

function Footer() {
  return (
    <footer className="footer px-24 py-3">
      <p className="footer-copyright mr-5 text-lg">© 2025 Needle&Stitch, Усі права захищено.</p>
      <div className="footer-content gap-5">
        <Link className="text-center text-lg" to="/">
          Політика конфіденційності
        </Link>
        <div className="footer-content-divider"></div>
        <Link className="text-center text-lg" to="/">
          Юридична інформація
        </Link>
      </div>
    </footer>
  );
}

export default Footer;