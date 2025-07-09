import CustomerType from "./customer-type";
import ClothingItemType from "./clothing-item-type";
import ShippingInfoType from "./shipping-info-type";


type OrderType = {
  id?: string,
  customer: CustomerType,
  clothingItem: ClothingItemType,
  shippingInfo: ShippingInfoType,
  orderDate?: string,
  status?: string,
};

export default OrderType;