import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import FabricType from "../../types/fabric-type";


const initialState: any = {
  fabrics: []
};


export const getFabrics = createAsyncThunk(
  "fabrics/getFabrics",
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get(
        `${import.meta.env.VITE_SERVER_PATH}fabrics`,
        { withCredentials: true }
      );

      if (response.status === 200) {
        return response.data as FabricType[];
      }
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);


const fabricsSlice = createSlice({
  name: "fabrics",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
    .addCase(getFabrics.pending, (state) => {
      console.log("getFabrics pending");
    })
    .addCase(getFabrics.fulfilled, (state, action) => {
      console.log("getFabrics success");
      state.fabrics = action.payload;
    })
    .addCase(getFabrics.rejected, (state, action) => {
      console.log("getFabrics rejected");
    })
  }
}); 

export default fabricsSlice.reducer;