import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../state/store";
import { useEffect, useRef } from "react";
import {
  getOrders,
  getOrdersByCustomer,
  removeOrder,
} from "../../state/orders/orders-slice";
import { useNavigate } from "react-router-dom";
import OrderType from "../../types/order-type";
import formatDate from "../../utils/formatDate";
import "./OrderList.css";
import CustomerType from "../../types/customer-type";
import { getCustomerByUserId } from "../../state/customers/customer-slice";

function OrderList() {
  const currentUser = useSelector(
    (state: RootState) => state.users.currentUser
  );
  const dispatch = useDispatch<AppDispatch>();
  const orders: Array<OrderType> = useSelector((state: RootState) => {
    return state.orders.orders;
  });
  const customers: Array<CustomerType> = useSelector((state: RootState) => {
    return state.customers.customers;
  });
  const navigate = useNavigate();
  const effectRan = useRef(false);

  useEffect(() => {
    if (effectRan.current === false) {
      if (currentUser.role.name === "ROLE_ADMIN") {
        dispatch(getOrders());
      } else {
        dispatch(getCustomerByUserId(currentUser.id))
        .then((action: { payload: any; }) => {
          console.log("Payload:", action.payload.id);
          dispatch(getOrdersByCustomer(action.payload.id));
        })
        .catch((error: any) => {
          console.error("Error:", error);
        });

      }

      return () => {
        effectRan.current = true;
      };
    }
  }, []);

  const editOrderHandler = async (orderId: string) => {
    navigate(`/edit-order/${orderId}`);
  };

  const removeOrderHandler = async (orderId: string) => {
    //await dispatch(removeOrder(orderId));
    dispatch(getOrders());
  };

  return (
    <div className="order-list-container max-h-full">
      <table className="order-list w-full">
        <thead className="order-list-thead">
          <tr>
            <th className="order-list-th px-8 py-1 text-left text-sm">ID</th>
            <th className="order-list-th px-8 py-1 text-left text-sm">
              ID Товару
            </th>
            <th className="order-list-th px-8 py-1 text-left text-sm">
              ID Клієнта
            </th>
            <th className="order-list-th px-8 py-1 text-left text-sm">
              Адреса доставки
            </th>
            <th className="order-list-th px-8 py-1 text-left text-sm">Сума</th>
            <th className="order-list-th px-8 py-1 text-left text-sm">
              Статус замовлення
            </th>
            <th className="order-list-th px-8 py-1 text-left text-sm">
              Дата замовлення
            </th>
            <th className="px-5 py-5"></th>
            <th className="px-5 py-5"></th>
          </tr>
        </thead>
        <tbody>
          {orders?.map((order: OrderType) => {
            return (
              <tr key={order.id}>
                <td className="order-list-td px-8 py-2 text-sm">{order.id}</td>
                <td className="order-list-td px-8 py-2 text-sm">
                  {order.clothingItem.id}
                </td>
                <td className="order-list-td px-8 py-2 text-sm">
                  {order.customer.id}
                </td>
                <td className="order-list-td px-8 py-2 text-sm">
                  {order.shippingInfo.country}, {order.shippingInfo.city},{" "}
                  {order.shippingInfo.postService.name},{" "}
                  {order.shippingInfo.postalCode}, {order.shippingInfo.region}
                </td>
                <td className="order-list-td px-8 py-2 text-sm">
                  {order.clothingItem.price}₴
                </td>
                <td className="order-list-td px-8 py-2 text-sm">
                  {order.status}
                </td>
                <td className="order-list-td px-8 py-2 text-sm">
                  {order.orderDate ? formatDate(order.orderDate) : "N/A"}
                </td>
                {currentUser?.role?.name === "ROLE_ADMIN" && (
                  <>
                    <td className="order-list-td">
                      <button
                        className="order-list-edit-order-button px-1 py-1 w-9 text-sm"
                        onClick={() => order.id && editOrderHandler(order.id)}
                      >
                        ✎
                      </button>
                    </td>
                    <td className="order-list-td">
                      <button
                        className="order-list-remove-order-button ml-1 mr-8 px-1 py-1 w-9 text-sm"
                        onClick={() => order.id && removeOrderHandler(order.id)}
                      >
                        ✖
                      </button>
                    </td>
                  </>
                )}
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}

export default OrderList;
