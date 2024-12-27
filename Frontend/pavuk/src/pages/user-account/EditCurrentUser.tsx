import Navbar from "../../components/common/Navbar";
import EditCurrentUserForm from "../../components/user-account/EditCurrentUserForm";
import Footer from "../../components/common/Footer";


function EditCurrentUser() {
  return (
    <>
      <Navbar userAccountLink={true} clothingItemsLink={true}></Navbar>
      <EditCurrentUserForm></EditCurrentUserForm>
      <Footer></Footer>
    </>
  );
}

export default EditCurrentUser;