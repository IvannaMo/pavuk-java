import { useNavigate } from "react-router-dom";
import { AppDispatch, RootState } from "../../state/store";
import { useDispatch, useSelector } from "react-redux";
import { showSignInForm } from "../../state/ui/ui-slice";
import ClothingItemType from "../../types/clothing-item-type";
import { sliceClothingItemDescription } from "../../utils/slice";
import "./ClothingItemsGalleryItem.css";


function ClothingItemsGalleryItem({ clothingItem }: { clothingItem: ClothingItemType }) {
  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const primaryImage = Array.from(clothingItem.images).find(image => image.isPrimary);
  const imageUrl = primaryImage ? primaryImage.url : Array.from(clothingItem.images)[0]?.url;
  const currentUser = useSelector((state: RootState) => state.users.currentUser);
  
  const сhooseHandler = () => {
    if (currentUser) {
      navigate(`/clothing-items/customization/${clothingItem.id}`, { state: { clothingItem } });
    } 
    else {
      dispatch(showSignInForm());
    }
  };

  return (
    <div className="clothing-gallery-item-container max-w-72">
      <img className="clothing-gallery-item-image w-max h-max min-w-64" src={`${import.meta.env.VITE_SERVER_PATH}${imageUrl}`} alt={clothingItem.name}/>
      <div className="px-5 pt-2 pb-4">
        <h4 className="clothing-gallery-item-heading text-2xl">{clothingItem.name}</h4>
        <p className="clothing-gallery-item-subtitle text-lg">{sliceClothingItemDescription(clothingItem.description)}</p>
        <p className="clothing-gallery-item-price mt-2">
          <span className="text-xl">{clothingItem.price}</span>
          <span className="clothing-gallery-item-price-currency text-md">₴</span>
        </p>

        <div className="flex justify-end mt-4">
          <button className="clothing-gallery-item-button px-5 py-2 text-lg" onClick={сhooseHandler}>
            Обрати
          </button>
        </div>
      </div>
    </div>
  );
}

export default ClothingItemsGalleryItem;