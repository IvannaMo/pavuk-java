import { useParams } from "react-router-dom";
import Navbar from "../../components/common/Navbar";
import Footer from "../../components/common/Footer";
import EditClothingItemForm from "../../components/user-account/EditClothingItemForm";


function EditClothingItem() {
  const { clothingItemId } = useParams<{ clothingItemId: string }>();
  
  return (
    <>
      <Navbar userAccountLink={true} clothingItemsLink={true}></Navbar>
      <EditClothingItemForm clothingItemId={clothingItemId}></EditClothingItemForm>
      <Footer></Footer>
    </>
  );
}

export default EditClothingItem;