import ImageType from "./image-type";


type ClothingItemType = {
  id: string,
  name: string,
  description: string,
  price: number,
  imageUrl: string,
  images: Set<ImageType>,
};

export default ClothingItemType;