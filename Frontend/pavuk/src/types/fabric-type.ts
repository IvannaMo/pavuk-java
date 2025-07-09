import ImageType from "./image-type";
import ColorType from "./color-type";


type FabricType = {
  id?: string,
  name: string,
  image: ImageType,
  colors: ColorType[],
};

export default FabricType;