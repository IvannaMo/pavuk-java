import { configureStore } from "@reduxjs/toolkit";
import uiSliceReducer from "./ui/ui-slice";
import usersSliceReducer from "./users/users-slice";
import clothingItemsSliceReducer from "./clothing-items/clothing-items-slice";
import ordersSliceReducer from "./orders/orders-slice";


export const store = configureStore({
  reducer: {
    ui: uiSliceReducer,
    users: usersSliceReducer,
    clothingItems: clothingItemsSliceReducer,
    orders: ordersSliceReducer
  }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;