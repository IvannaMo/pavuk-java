import { useParams } from "react-router-dom";
import Navbar from "../../components/common/Navbar";
import EditUserForm from "../../components/user-account/EditUserForm";
import Footer from "../../components/common/Footer";


function EditUser() {
  const { userId } = useParams<{ userId: string }>();
  
  return (
    <>
      <Navbar userAccountLink={true} clothingItemsLink={true}></Navbar>
      <EditUserForm userId={userId}></EditUserForm>
      <Footer></Footer>
    </>
  );
}

export default EditUser;