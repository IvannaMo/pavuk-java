import Navbar from "../../components/common/Navbar";
import CreateUserForm from "../../components/user-account/CreateUserForm";
import Footer from "../../components/common/Footer";


function CreateUser() {
  return (
    <>
      <Navbar userAccountLink={true} clothingItemsLink={true}></Navbar>
      <CreateUserForm></CreateUserForm>
      <Footer></Footer>
    </>
  );
}

export default CreateUser;