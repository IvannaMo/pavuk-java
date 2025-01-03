import ClothingItemType from "./clothing-item-type";


type CategoryType = {
  id?: number,
  name: string,
  clothingItems?: ClothingItemType[],
};

export default CategoryType;