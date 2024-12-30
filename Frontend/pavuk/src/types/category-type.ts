import ClothingItemType from "./clothing-item-type";


type CategoryType = {
  id?: number,
  name: string,
  clothingItems?: Set<ClothingItemType>,
};

export default CategoryType;