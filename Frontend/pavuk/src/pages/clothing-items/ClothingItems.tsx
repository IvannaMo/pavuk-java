import Navbar from "../../components/common/Navbar";
import ClothingItemsGallery from "../../components/clothing-items/ClothingItemsGallery";
import Footer from "../../components/common/Footer";


function ClothingItems() {
  return (
    <div className="flex flex-col h-full">
      <Navbar userAccountLink={true} clothingItemsLink={false}></Navbar>
      <ClothingItemsGallery></ClothingItemsGallery>
      <Footer></Footer>
    </div>
  );
}

export default ClothingItems;