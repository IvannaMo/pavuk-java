import Navbar from "../../components/common/Navbar";
import UserInfo from "../../components/user-account/UserInfo";
import Footer from "../../components/common/Footer";


function UserAccount() {
  return (
    <>
      <Navbar productsLink={true}></Navbar>
      <UserInfo></UserInfo>
      <Footer></Footer>
    </>
  );
}

export default UserAccount;