import PostServiceType from "./post-service-type";


type ShippingInfoType = {
  id?: number,
  country: string,
  region: string,
  city: string,
  postalCode: string,
  postService: PostServiceType,
};

export default ShippingInfoType;