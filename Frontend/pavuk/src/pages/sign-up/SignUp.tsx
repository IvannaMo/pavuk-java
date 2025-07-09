import Navbar from "../../components/common/Navbar";
import SignUpForm from "../../components/sign-up/SignUpForm";
import Footer from "../../components/common/Footer";


function SignUp() {
  return (
    <>
      <Navbar userAccountLink={true} clothingItemsLink={true}></Navbar>
      <SignUpForm></SignUpForm>
      <Footer></Footer>
    </>
  );
}

export default SignUp;