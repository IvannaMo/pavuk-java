import ClothingItemType from "./clothing-item-type";


type AdminClothingItemType = Omit<ClothingItemType, "id"> & {
  id: string;
};

export default AdminClothingItemType;