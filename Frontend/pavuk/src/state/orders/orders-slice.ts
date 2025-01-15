import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import OrderType from "../../types/order-type";


const initialState: any = {
  orders: []
};


export const getOrders = createAsyncThunk(
  "orders/getOrders",
  async (_, { rejectWithValue }) => {
    try {
      const res = await axios.get(
        `${import.meta.env.VITE_SERVER_PATH}orders`,
        { withCredentials: true }
      );

      return res.data as OrderType[];
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const getOrdersByCustomer = createAsyncThunk(
  "orders/getOrdersByCustomer",
  async (customerId: string, { rejectWithValue }) => {
    try {
      const res = await axios.get(
        `${import.meta.env.VITE_SERVER_PATH}orders/customer/${customerId}`,
        { withCredentials: true }
      );
      // console.log(res.data);
      // console.log(`${import.meta.env.VITE_SERVER_PATH}orders/customer/${customerId}`);
      return res.data as OrderType[];

    } catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const createOrder = createAsyncThunk(
  "orders/createOrder",
  async (data: any, { rejectWithValue }) => {
    try {
      const response = await axios.post(
        `${import.meta.env.VITE_SERVER_PATH}orders/create`,
        data,
        { withCredentials: true }
      );
      console.log("response data:");
      console.log(response.data);
      if (response.status === 201) {
        return response.data as OrderType;
      }
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

// export const editOrder = createAsyncThunk(
//   "orders/editOrder",
//   async (editedOrder: OrderType, { rejectWithValue }) => {
//       try {
//         const res = await axios.put(
//           `${import.meta.env.VITE_SERVER_PATH}orders/${editedOrder.id}`, 
//           editedOrder
//         );
//         return res.data as OrderType;
//       } 
//       catch (error: any) {
//         return rejectWithValue(error.message);
//       }
//   }
// );

// export const removeOrder = createAsyncThunk(
//   "orders/removeOrder",
//   async (orderId: string, { rejectWithValue }) => {
//     try {
//       await axios.delete(`${import.meta.env.VITE_SERVER_PATH}orders/${orderId}`);
//       return orderId; 
//     } 
//     catch (error: any) {
//       return rejectWithValue(error.message);
//     }
//   }
// );


const ordersSlice = createSlice({
  name: "orders",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
    .addCase(getOrders.pending, (state) => {
      console.log("getOrders pending");
    })
    .addCase(getOrders.fulfilled, (state, action) => {
      console.log("getOrders success");
      state.orders = action.payload;
    })
    .addCase(getOrders.rejected, (state, action) => {
      console.log("getOrders rejected");
    })
    .addCase(createOrder.pending, (state) => {
      console.log("createOrder pending");
    })
    .addCase(createOrder.fulfilled, (state, action) => {
      console.log("createOrder success");
      state.orders.push(action.payload);
    })
    .addCase(createOrder.rejected, (state, action) => {
      console.log("createOrder rejected");
    })
    .addCase(getOrdersByCustomer.pending, (state) => {
      console.log("getOrdersByCustomer pending");
    })
    .addCase(getOrdersByCustomer.fulfilled, (state, action) => {
      console.log("getOrdersByCustomer success");
      state.orders = action.payload;
    })
    .addCase(getOrdersByCustomer.rejected, (state, action) => {
      console.log("getOrdersByCustomer rejected");
    });
    // .addCase(editOrder.pending, (state) => {
    //   console.log("editOrder pending");
    // })
    // .addCase(editOrder.fulfilled, (state, action) => {
    //   console.log("editOrder success");
    //   const updatedOrder = action.payload;
    //   state.orders = state.orders.map((order: OrderType) =>
    //     order.id === updatedOrder.id ? updatedOrder : order
    //   );
    // })
    // .addCase(editOrder.rejected, (state, action) => {
    //   console.log("editOrder rejected");
    // })
    // .addCase(removeOrder.pending, (state) => {
    //   console.log("removeProduct pending");
    // })
    // .addCase(removeOrder.fulfilled, (state, action) => {
    //   console.log("removeProduct success");
    //   state.orders = state.orders.filter((order: OrderType) => order.id !== action.payload);
    // })
    // .addCase(removeOrder.rejected, (state, action) => {
    //   console.log("removeProduct rejected");
    // });
  }
}); 

export default ordersSlice.reducer;