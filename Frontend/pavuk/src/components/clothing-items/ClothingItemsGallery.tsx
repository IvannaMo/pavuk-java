import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { getClothingItems } from "../../state/clothing-items/clothing-items-slice";
import ClothingItemType from "../../types/clothing-item-type";
import ClothingItemsGalleryItem from "./ClothingItemsGalleryItem";
import "./ClothingItemsGallery.css";


function ClothingItemsGallery() {
  const dispatch = useDispatch<AppDispatch>();
  const clothingItems: Array<ClothingItemType> = useSelector((state: RootState) => state.clothingItems.clothingItems);

  const [sortOption, setSortOption] = useState("priceAsc");
  const effectRan = useRef(false);

  useEffect(() => {
    if (effectRan.current === false) {
      dispatch(getClothingItems());

      return () => {
        effectRan.current = true;
      }
    }
  }, []);

  const sortHandler = (clothingItems: Array<ClothingItemType>): Array<ClothingItemType> => {
    switch (sortOption) {
      case "priceAsc":
        return clothingItems.slice().sort((clothingItem1, clothingItem2) => clothingItem1.price - clothingItem2.price);
      case "priceDesc":
        return clothingItems.slice().sort((clothingItem1, clothingItem2) => clothingItem2.price - clothingItem1.price);
      case "nameAsc":
        return clothingItems.slice().sort((clothingItem1, clothingItem2) => clothingItem1.name.localeCompare(clothingItem2.name));
      default:
        return clothingItems;
    }
  }

  const sortedClothingItems = sortHandler(clothingItems);

  return (
    <section className="clothing-gallery-section pt-11 pb-14">
      {sortedClothingItems.length ? (
        <div className="flex flex-col mx-auto w-fit">
          <div>
            <select value={sortOption} onChange={(e) => setSortOption(e.target.value)} className="clothing-gallery-sort px-4 py-2 text-lg">
              <option value="priceAsc">Від дешевих до дорогих</option>
              <option value="priceDesc">Від дорогих до дешевих</option>
              <option value="nameAsc">За назвою</option>
            </select>
          </div>

          <div className="grid grid-cols-4 justify-items-center justify-center gap-y-20 gap-x-11 mt-5 w-fit">
            {sortedClothingItems.map((clothingItem: ClothingItemType) => {
              return (
                <div className="col" key={clothingItem.id}>
                  <ClothingItemsGalleryItem clothingItem={clothingItem}/>
                </div>
              );
            })}
          </div>
        </div>
      ) : (
        <p className="mt-6 w-full text-lg">Список порожній</p>
      )}
    </section>
  );
}

export default ClothingItemsGallery;