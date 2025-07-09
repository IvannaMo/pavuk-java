import Navbar from "../../components/common/Navbar";
import UserInfo from "../../components/user-account/UserInfo";
import Footer from "../../components/common/Footer";


function UserAccount() {
  return (
    <>
      <Navbar userAccountLink={false} clothingItemsLink={true}></Navbar>
      <UserInfo></UserInfo>
      <Footer></Footer>
    </>
  );
}

export default UserAccount;