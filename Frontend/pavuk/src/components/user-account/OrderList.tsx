import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../state/store";
import { useEffect, useRef } from "react";
import { getOrders, removeOrder } from "../../state/orders/orders-slice";
import { useNavigate } from "react-router-dom";
import OrderType from "../../types/order-type";
import formatDate from "../../utils/formatDate";
import "./OrderList.css";


function OrderList() {
  const dispatch = useDispatch<AppDispatch>();
  const orders: Array<OrderType> = useSelector((state: RootState) => {
    return state.orders.orders;
  });
  const navigate = useNavigate();
  const effectRan = useRef(false);

  useEffect(() => {
    if (effectRan.current === false) {
      dispatch(getOrders());
      
      return () => {
        effectRan.current = true;
      }
    }
  }, []);

  const editOrderHandler = async (orderId: string) => {
    navigate(`/edit-order/${orderId}`);
  };

  const removeOrderHandler = async (orderId: string) => {
    await dispatch(removeOrder(orderId));
    dispatch(getOrders());
  };

  return (
    <div className="order-list-container max-h-full">
      <table className="order-list w-full">
        <thead className="order-list-thead">
          <tr>
            <th className="order-list-th px-8 py-1 text-left text-sm">ID</th>
            <th className="order-list-th px-8 py-1 text-left text-sm">ID Товару</th>
            <th className="order-list-th px-8 py-1 text-left text-sm">ID Клієнта</th>
            <th className="order-list-th px-8 py-1 text-left text-sm">ID Швачки</th>
            <th className="order-list-th px-8 py-1 text-left text-sm">Сума</th>
            <th className="order-list-th px-8 py-1 text-left text-sm">Коментарі до замовлення</th>
            <th className="order-list-th px-8 py-1 text-left text-sm">Дата замовлення</th>
            <th className="order-list-th px-8 py-1 text-left text-sm">Дата підтвердження</th>
            <th className="px-5 py-5"></th>
            <th className="px-5 py-5"></th>
            </tr>
        </thead>
        <tbody>
          {orders?.map((order: OrderType) => {
            return (
              <tr key={order.id}>
                <td className="order-list-td px-8 py-2 text-sm">{order.id}</td>
                <td className="order-list-td px-8 py-2 text-sm">{order.productId}</td>
                <td className="order-list-td px-8 py-2 text-sm">{order.clientId}</td>
                <td className="order-list-td px-8 py-2 text-sm">{order.seamstressId}</td>
                <td className="order-list-td px-8 py-2 text-sm">{order.price}</td>
                <td className="order-list-td px-8 py-2 text-sm">{order.notes}</td>
                <td className="order-list-td px-8 py-2 text-sm">{formatDate(order.orderDate)}</td>
                <td className="order-list-td px-8 py-2 text-sm">{order.confirmDate ? formatDate(order.confirmDate) : "не підтверджено"}</td>
                <td className="order-list-td">
                  <button className="order-list-edit-order-button px-1 py-1 w-9 text-sm"  onClick={() => editOrderHandler(order.id)}>✎</button>
                </td>
                <td className="order-list-td">
                  <button className="order-list-remove-order-button ml-1 mr-8 px-1 py-1 w-9 text-sm" onClick={() => removeOrderHandler(order.id)}>✖</button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>   
    </div>  
  );
}

export default OrderList;