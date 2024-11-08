import Hero from "../../components/home/Hero";
import AboutUs from "../../components/home/AboutUs";
import Footer from "../../components/common/Footer";
import HomeNavbar from "../../components/home/HomeNavbar";


function Home() {
  return (
    <>
      <HomeNavbar></HomeNavbar>
      <Hero></Hero>
      <AboutUs></AboutUs>
      <Footer></Footer>
    </>
  );
}

export default Home;