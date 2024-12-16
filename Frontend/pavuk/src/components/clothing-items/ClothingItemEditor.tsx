import { Link, useLocation } from "react-router-dom";
import ClothingItemType from "../../types/clothing-item-type";
import "./ClothingItemEditor.css";


function ClothingItemEditor() {
  const location = useLocation();
  const { clothingItem } = location.state as { clothingItem: ClothingItemType };
  const primaryImage = Array.from(clothingItem.images).find(image => image.isPrimary);
  const imageUrl = primaryImage ? primaryImage.url : Array.from(clothingItem.images)[0]?.url;
  
  return (
    <>
      <section className="clothing-editor-section pt-11 pb-14">
        <div className="clothing-editor w-8/12 h-full">
          <div className="h-full aspect-square px-20 py-12">
            <img className="clothing-editor-image" src={`${import.meta.env.VITE_SERVER_PATH}${imageUrl}`} alt={clothingItem.name}/>
          </div>

          <div className="pr-16 pt-9 pb-12 flex-1"> 
            <h4 className="clothing-editor-heading text-2xl">{clothingItem.name}</h4>
            <p className="clothing-editor-subtitle text-lg">{clothingItem.description}</p>
            <p className="clothing-editor-price mt-2">
              <span className="text-xl">{clothingItem.price}</span>
              <span className="clothing-editor-price-currency text-md">₴</span>
            </p>

            <p className="clothing-gallery-item-info mt-5 text-lg">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Nam repudiandae dolorum numquam consequuntur, doloribus et aspernatur ipsam enim, itaque adipisci in, temporibus veritatis architecto porro ipsa voluptatibus aperiam nulla consectetur?
            </p>

            <div className="flex justify-end mt-7">
              <Link className="clothing-editor-button text-lg px-8 py-3" to={`/seamstress-confirmed/${clothingItem.id}`} state={{ clothingItem }}>
                Підтвердити
              </Link>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default ClothingItemEditor;