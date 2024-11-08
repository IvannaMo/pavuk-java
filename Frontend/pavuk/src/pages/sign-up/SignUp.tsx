import Navbar from "../../components/common/Navbar";
import SignUpForm from "../../components/sign-up/SignUpForm";
import Footer from "../../components/common/Footer";


function SignUp() {
  return (
    <>
      <Navbar productsLink={true}></Navbar>
      <SignUpForm></SignUpForm>
      <Footer></Footer>
    </>
  );
}

export default SignUp;