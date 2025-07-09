import { ErrorMessage, Field, Formik } from "formik";
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { hideSignInForm } from "../../state/ui/ui-slice";
import { editOrder } from "../../state/orders/orders-slice";
import DatePicker from "../common/DatePicker";
import UserType from "../../types/user-type";
import ClothingItemType from "../../types/clothing-item-type";
import OrderType from "../../types/order-type";
import "./EditOrderForm.css";


interface EditOrderFormProps {
  orderId: string | undefined;
}

function EditOrderForm({ orderId }: EditOrderFormProps) {
  const [isFormValid, setIsFormValid] = useState(false);
  const dispatch = useDispatch<AppDispatch>();
  const products: Array<ClothingItemType> = useSelector((state: RootState) => {
    return state.products.products;
  });
  const users: Array<UserType> = useSelector((state: RootState) => {
    return state.users.users;
  });
  const currentUser = useSelector((state: RootState) => state.users.currentUser);
  const signUpError = useSelector((state: RootState) => state.users.signUpError);
  const navigate = useNavigate();
  const effectRan = useRef(false);

  const orders = useSelector((state: RootState) => state.orders.orders);
  const order = orders.find((order: OrderType) => order.id === orderId);
  
  useEffect(() => {
    if (effectRan.current === false) {
      dispatch(hideSignInForm());

      return () => {
        effectRan.current = true;
      }
    }
  }, []);

  const editHandler = async (data: any) => {
    const tempOrder = { ...order, ...data };
    
    const resultAction = await dispatch(editOrder(tempOrder));
    if (editOrder.fulfilled.match(resultAction)) {
      navigate("/user-account");
    }
  };

  return (
    <section className="edit-order-form-container pt-11 pb-14">
      <Formik 
        initialValues={{
          productId: order.productId,
          clientId: order.clientId,
          seamstressId: order.seamstressId,
          price: order.price,
          imageUrl: order.imageUrl,
          notes: order.notes,
          orderDate: new Date(order.orderDate),
          confirmDate: order.confirmDate ? new Date(order.confirmDate) : "",
        }}
        validationOnInput
        onSubmit={editHandler} 
      >
        {({values, errors, touched, isValid, handleSubmit}) => {
          setIsFormValid(isValid);

          return (
            <form onSubmit={handleSubmit} className="edit-order-form px-16 pt-9 pb-12 w-6/12">
              <h2 className="edit-order-form-title mb-4 w-full text-center text-3xl">Редагування замовлення</h2>

              {signUpError && <p className="edit-order-form-field-error mt-3 mb-2 text-md">{signUpError}</p>}

              <div className="w-full">
                <label className="edit-order-form-label text-xl" htmlFor="productId">Товар:</label>
                <Field
                  className="edit-order-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="productId"
                  as="select"
                  id="productId"
                >
                  {products.map((product: ClothingItemType) => (
                    <option key={product.id} value={product.id}>{product.id} {product.name}</option>
                  ))}
                </Field>
                <ErrorMessage
                  className="edit-order-form-field-error mt-1 text-md"
                  name="productId"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-order-form-label text-xl" htmlFor="clientId">Клієнт:</label>
                <Field
                  className="edit-order-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="clientId"
                  as="select"
                  id="clientId"
                >
                  {users.filter(user => user.id !== currentUser.id).map((user: UserType) => (
                    <option key={user.id} value={user.id}>{user.id} {user.name} {user.surname}</option>
                  ))}
                </Field>
                <ErrorMessage
                  className="edit-order-form-field-error mt-1 text-md"
                  name="clientId"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-order-form-label text-xl" htmlFor="seamstressId">Швачка:</label>
                <Field
                  className="edit-order-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="seamstressId"
                  as="select"
                  id="seamstressId"
                >
                  {users.filter(user => user.id !== currentUser.id).map((user: UserType) => (
                    <option key={user.id} value={user.id}>{user.id} {user.name} {user.surname}</option>
                  ))}
                </Field>
                <ErrorMessage
                  className="edit-order-form-field-error mt-1 text-md"
                  name="seamstressId"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-order-form-label text-xl" htmlFor="price">Сума:</label>
                <Field
                  className="edit-order-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="price"
                  type="text"
                  id="price"
                />
                <ErrorMessage
                  className="edit-order-form-field-error mt-1 text-md"
                  name="price"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-order-form-label text-xl" htmlFor="imageUrl">URL-адреса зображення:</label>
                <Field
                  className="edit-order-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="imageUrl"
                  type="text"
                  id="imageUrl"
                />
                <ErrorMessage
                  className="edit-order-form-field-error mt-1 text-md"
                  name="imageUrl"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-order-form-label text-xl" htmlFor="notes">Коментарі до замовлення:</label>
                <Field
                  className="edit-order-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="notes"
                  type="text"
                  id="notes"
                />
                <ErrorMessage
                  className="edit-order-form-field-error mt-1 text-md"
                  name="notes"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-order-form-label text-xl" htmlFor="orderDate">Дата замовлення:</label>
                <DatePicker
                  className="edit-order-form-field mt-2 px-3 py-1 w-full text-lg"
                  name="orderDate"
                  id="orderDate"
                />
                <ErrorMessage
                  className="edit-order-form-field-error mt-1 text-md"
                  name="orderDate"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-order-form-label text-xl" htmlFor="confirmDate">Дата підтвердження:</label>
                <DatePicker
                  className="edit-order-form-field mt-2 px-3 py-1 w-full text-lg"
                  name="confirmDate"
                  id="confirmDate"
                />
                <ErrorMessage
                  className="edit-order-form-field-error mt-1 text-md"
                  name="confirmDate"
                  component="div"
                />
              </div>

              <button className={`${isFormValid ? "sign-in-form-sign-in-button" : "sign-in-form-sign-in-button-disabled"} mx-auto mt-10 px-8 py-3 w-9/12 text-xl`} type="submit" disabled={!isFormValid}>
                Редагувати
              </button>
            </form>
          );
        }}
      </Formik>
    </section>
  );
}

export default EditOrderForm;