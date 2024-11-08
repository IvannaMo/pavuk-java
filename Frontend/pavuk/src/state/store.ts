import { configureStore } from "@reduxjs/toolkit";
import uiSliceReducer from "./ui/ui-slice";
import usersSliceReducer from "./users/users-slice";
import productsSliceReducer from "./products/products-slice";
import ordersSliceReducer from "./orders/orders-slice";


export const store = configureStore({
  reducer: {
    ui: uiSliceReducer,
    users: usersSliceReducer,
    products: productsSliceReducer,
    orders: ordersSliceReducer
  }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;