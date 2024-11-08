type OrderType = {
  id: string,
  productId: string,
  clientId: string,
  seamstressId: string,
  price: number,
  imageUrl: string,
  notes: string,
  orderDate: string,
  confirmDate: string,
  isActive: boolean
};

export default OrderType;