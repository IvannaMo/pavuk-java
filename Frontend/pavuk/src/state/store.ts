import { configureStore } from "@reduxjs/toolkit";
import uiSliceReducer from "./ui/ui-slice";
import usersSliceReducer from "./users/users-slice";
import clothingItemsSliceReducer from "./clothing-items/clothing-items-slice";
import fabricsSliceReducer from "./fabrics/fabrics-slice";
import ordersSliceReducer from "./orders/orders-slice";
import customerSliceReducer from "./customers/customer-slice";
import shippingInfoReducer from "./shipping-info/shipping-info-slice";


export const store = configureStore({
  reducer: {
    ui: uiSliceReducer,
    users: usersSliceReducer,
    clothingItems: clothingItemsSliceReducer,
    fabrics: fabricsSliceReducer,
    orders: ordersSliceReducer,
    customers: customerSliceReducer,
    shippingInfo: shippingInfoReducer,
  }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;