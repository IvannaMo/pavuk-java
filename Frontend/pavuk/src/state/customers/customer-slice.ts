import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import CustomerType from "../../types/customer-type";


const initialState: any = {
  customers: []
};



export const getCustomerByUserId = createAsyncThunk(
  "orders/getCustomerByUserId",
  async (userId: string, { rejectWithValue }) => {
    try {
      const res = await axios.get(
        `${import.meta.env.VITE_SERVER_PATH}customers/user/${userId}`,
        { withCredentials: true }
      );
      console.log(res.data);
    //   console.log(`${import.meta.env.VITE_SERVER_PATH}customers/user/${userId}`);
      return res.data as CustomerType[];

    } catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);


const customersSlice = createSlice({
  name: "customers",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
    .addCase(getCustomerByUserId.pending, (state) => {
      console.log("getCustomerByUserId pending");
    })
    .addCase(getCustomerByUserId.fulfilled, (state, action) => {
      console.log("getCustomerByUserId success");
      state.customers = action.payload;
    })
    .addCase(getCustomerByUserId.rejected, (state, action) => {
      console.log("getCustomerByUserId rejected");
    });
  }
}); 

export default customersSlice.reducer;