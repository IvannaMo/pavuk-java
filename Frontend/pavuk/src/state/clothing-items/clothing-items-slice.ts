import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import ClothingItemType from "../../types/clothing-item-type";


const initialState: any = {
  clothingItems: []
};


export const getClothingItems = createAsyncThunk(
  "clothingItems/getClothingItems",
  async (_, { rejectWithValue }) => {
    try {
      const res = await axios.get(`${import.meta.env.VITE_SERVER_PATH}clothing-items`);

      return res.data as ClothingItemType[];
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const createClothingItem = createAsyncThunk(
  "clothingItems/createClothingItem",
  async (data: ClothingItemType, { rejectWithValue }) => {
    try {
      const response = await axios.post(
        import.meta.env.VITE_SERVER_PATH + "products",
        data
      );

      if (response.status === 201) {
        return response.data as ClothingItemType;
      }
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const editClothingItem = createAsyncThunk(
  "clothingItems/editClothingItem",
  async (editedProduct: ClothingItemType, { rejectWithValue }) => {
      try {
        const res = await axios.put(
            `${import.meta.env.VITE_SERVER_PATH}products/${editedProduct.id}`, 
            editedProduct
        );
        return res.data as ClothingItemType;
      } 
      catch (error: any) {
        return rejectWithValue(error.message);
      }
  }
);

export const removeClothingItem = createAsyncThunk(
  "clothingItems/removeClothingItem",
  async (productId: string, { rejectWithValue }) => {
    try {
      await axios.delete(`${import.meta.env.VITE_SERVER_PATH}products/${productId}`);
      return productId; 
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);


const productsSlice = createSlice({
  name: "clothingItems",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
    .addCase(getClothingItems.pending, (state) => {
      console.log("getClothingItems pending");
    })
    .addCase(getClothingItems.fulfilled, (state, action) => {
      console.log("getClothingItems success");
      state.clothingItems = action.payload;
    })
    .addCase(getClothingItems.rejected, (state, action) => {
      console.log("getClothingItems rejected");
    })
    .addCase(createClothingItem.pending, (state) => {
      console.log("createClothingItem pending");
    })
    .addCase(createClothingItem.fulfilled, (state, action) => {
      console.log("createClothingItem success");
      state.clothingItems.push(action.payload);
    })
    .addCase(createClothingItem.rejected, (state, action) => {
      console.log("createClothingItem rejected");
    })
    .addCase(editClothingItem.pending, (state) => {
      console.log("editClothingItem pending");
    })
    .addCase(editClothingItem.fulfilled, (state, action) => {
      console.log("editClothingItem success");
      const updatedClothingItem = action.payload;
      state.clothingItems = state.clothingItems.map((clothingItem: ClothingItemType) =>
        clothingItem.id === updatedClothingItem.id ? updatedClothingItem : clothingItem
      );
    })
    .addCase(editClothingItem.rejected, (state, action) => {
      console.log("editClothingItem rejected");
    })
    .addCase(removeClothingItem.pending, (state) => {
      console.log("removeClothingItem pending");
    })
    .addCase(removeClothingItem.fulfilled, (state, action) => {
      console.log("removeClothingItem success");
      state.clothingItems = state.clothingItems.filter((clothingItem: ClothingItemType) => 
        clothingItem.id !== action.payload
      );
    })
    .addCase(removeClothingItem.rejected, (state, action) => {
      console.log("removeClothingItem rejected");
    });
  }
}); 

export default productsSlice.reducer;