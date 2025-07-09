import { useLocation } from "react-router-dom";
import OrderType from "../../types/order-type";
import "./OrderPreview.css";


function OrderPreview() {
  const location = useLocation();
  const { order } = location.state as { order: OrderType };

  return (
    <section className="order-preview-section pt-11 pb-14">
      <div className="order-preview px-16 pt-9 pb-12 w-6/12 text-center">
        <h2 className="order-preview-heading text-3xl">Дякуємо за замовлення!</h2>
        <p className="order-preview-paragraph mt-3 text-xl">
          Ваше замовлення на 
          <span className="order-preview-clothing-name ml-1">{order.clothingItem.name}</span> на суму 
          <span className="order-preview-clothing-price ml-1">{order.clothingItem.price}</span>
          <span className="order-preview-clothing-price-currency">₴</span> було успішно оформлене. 
        </p>
      </div>
    </section>
  );
}

export default OrderPreview;