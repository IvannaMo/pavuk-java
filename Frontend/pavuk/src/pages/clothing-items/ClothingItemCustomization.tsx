import Navbar from "../../components/common/Navbar";
import ClothingItemEditor from "../../components/clothing-items/ClothingItemEditor";
import Footer from "../../components/common/Footer";


function ClothingItemCustomization() {
  return (
    <>
      <Navbar userAccountLink={true} clothingItemsLink={false}></Navbar>
      <ClothingItemEditor></ClothingItemEditor>
      <Footer></Footer>
    </>
  );
}

export default ClothingItemCustomization;