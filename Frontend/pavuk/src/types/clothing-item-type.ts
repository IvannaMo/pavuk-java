import CategoryType from "./category-type";
import ImageType from "./image-type";


export enum ClothingItemTypeEnum {
  BASIC = "BASIC",
  USER = "USER",
}

type ClothingItemType = {
  id?: string,
  category: CategoryType,
  name: string,
  description: string,
  price: number,
  images: ImageType[],
  type: ClothingItemTypeEnum,
  status: "PENDING" | "ACTIVE" | "INACTIVE" | "REMOVED",
};

export default ClothingItemType;