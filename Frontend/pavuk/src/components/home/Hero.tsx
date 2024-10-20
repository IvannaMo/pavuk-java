import { Link } from "react-router-dom";
import seamstressSpiderCover from "../../assets/images/seamstress-spider.jpg";
import "./Hero.css";

function Hero() {
  return (
    <>
      <section className="hero-section">
        <img className="hero-img" src={seamstressSpiderCover}/>
        
        <div className="hero-content-container">
          <div className="hero-content px-48">
            <div className="mt-4 w-6/12">
              <h1 className="hero-content-heading text-5xl">
                Перший посередник ательє послуг в Україні
              </h1>
              <p className="hero-content-subtitle mt-5 text-3xl">
                Більше 100 000 перевірених фахівців
              </p>
              <Link className="hero-content-button mt-11 mb-3 px-8 py-4 text-2xl" to="/products">
                Cтворити замовлення
              </Link>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default Hero;