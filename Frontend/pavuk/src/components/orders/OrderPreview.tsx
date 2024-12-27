import { useLocation } from "react-router-dom";
import { useEffect, useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { createOrder } from "../../state/orders/orders-slice";
import ProductType from "../../types/clothing-item-type";
import "./OrderPreview.css";
import OrderType from "../../types/order-type";


function OrderPreview() {
  // const dispatch = useDispatch<AppDispatch>();
  // const currentUser = useSelector((state: RootState) => state.users.currentUser);
  const location = useLocation();
  // const { product } = location.state as { product: ProductType };
  // const effectRan = useRef(false);
  const { order } = location.state as { order: OrderType };

  // useEffect(() => {
  //   if (effectRan.current === false) {
  //     const orderData: any = {
  //       productId: product.id,
  //       clientId: currentUser.id,
  //       seamstressId: "",
  //       price: product.price,
  //       imageUrl: product.imageUrl,
  //       notes: "",
  //       orderDate: new Date().toISOString(),
  //       confirmDate: "",
  //       isActive: true
  //     };

  //     dispatch(createOrder(orderData));

  //     return () => {
  //       effectRan.current = true;
  //     }
  //   }
  // }, []);

  return (
    <section className="order-preview-section pt-11 pb-14">
      <div className="order-preview px-16 pt-9 pb-12 w-6/12 text-center">
        <h2 className="order-preview-heading text-3xl">Дякуємо за замовлення!</h2>
        <p className="order-preview-paragraph mt-3 text-xl">
          Ваше замовлення на 
          <span className="order-preview-clothing-name ml-1">{order.clothingItem.name}</span> на суму 
          <span className="order-preview-clothing-price ml-1">{order.clothingItem.price}</span>
          <span className="order-preview-clothing-price-currency">₴</span> було успішно оформлене. 
          Замовлення включає 1 одиницю товару. Ми зв'яжемося з вами найближчим часом для уточнення деталей оплати та доставки.
        </p>
      </div>
    </section>
  );
}

export default OrderPreview;