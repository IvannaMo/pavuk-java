import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import ProductType from "../../types/product-type";


const initialState: any = {
  products: []
};


export const getProducts = createAsyncThunk(
  "products/getProducts",
  async (_, { rejectWithValue }) => {
    try {
      const res = await axios.get(
        import.meta.env.VITE_SERVER_PATH + "products"
      );
      console.log(res.data);
      return res.data as ProductType[];
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const createProduct = createAsyncThunk(
  "products/createProduct",
  async (data: ProductType, { rejectWithValue }) => {
    try {
      const response = await axios.post(
        import.meta.env.VITE_SERVER_PATH + "products",
        data
      );

      if (response.status === 201) {
        return response.data as ProductType;
      }
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const editProduct = createAsyncThunk(
  "products/editProduct",
  async (editedProduct: ProductType, { rejectWithValue }) => {
      try {
        const res = await axios.put(
            `${import.meta.env.VITE_SERVER_PATH}products/${editedProduct.id}`, 
            editedProduct
        );
        return res.data as ProductType;
      } 
      catch (error: any) {
        return rejectWithValue(error.message);
      }
  }
);

export const removeProduct = createAsyncThunk(
  "products/removeProduct",
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
  name: "products",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
    .addCase(getProducts.pending, (state) => {
      console.log("getProducts pending");
    })
    .addCase(getProducts.fulfilled, (state, action) => {
      console.log("getProducts success");
      state.products = action.payload;
    })
    .addCase(getProducts.rejected, (state, action) => {
      console.log("getProducts rejected");
    })
    .addCase(createProduct.pending, (state) => {
      console.log("createProduct pending");
    })
    .addCase(createProduct.fulfilled, (state, action) => {
      console.log("createProduct success");
      state.products.push(action.payload);
    })
    .addCase(createProduct.rejected, (state, action) => {
      console.log("createProduct rejected");
    })
    .addCase(editProduct.pending, (state) => {
      console.log("editProduct pending");
    })
    .addCase(editProduct.fulfilled, (state, action) => {
      console.log("editProduct success");
      const updatedProduct = action.payload;
      state.products = state.products.map((product: ProductType) =>
        product.id === updatedProduct.id ? updatedProduct : product
      );
    })
    .addCase(editProduct.rejected, (state, action) => {
      console.log("editProduct rejected");
    })
    .addCase(removeProduct.pending, (state) => {
      console.log("removeProduct pending");
    })
    .addCase(removeProduct.fulfilled, (state, action) => {
      console.log("removeProduct success");
      state.products = state.products.filter((product: ProductType) => product.id !== action.payload);
    })
    .addCase(removeProduct.rejected, (state, action) => {
      console.log("removeProduct rejected");
    });
  }
}); 

export default productsSlice.reducer;