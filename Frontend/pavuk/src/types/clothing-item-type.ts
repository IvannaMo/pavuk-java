import CategoryType from "./category-type";
import ImageType from "./image-type";


type ClothingItemType = {
  id?: string,
  category: CategoryType,
  name: string,
  description: string,
  price: number,
  images: Set<ImageType>,
  status: "PENDING" | "ACTIVE" | "INACTIVE" | "REMOVED",
};

export default ClothingItemType;