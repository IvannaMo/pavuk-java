import Navbar from "../../components/common/Navbar";
import CreateClothingItemForm from "../../components/user-account/CreateClothingItemForm";
import Footer from "../../components/common/Footer";


function CreateClothingItem() {
  return (
    <>
      <Navbar userAccountLink={true} clothingItemsLink={true}></Navbar>
      <CreateClothingItemForm></CreateClothingItemForm>
      <Footer></Footer>
    </>
  );
}

export default CreateClothingItem;