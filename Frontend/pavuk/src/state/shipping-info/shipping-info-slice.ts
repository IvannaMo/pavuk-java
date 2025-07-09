import { createSlice } from "@reduxjs/toolkit";

const initialState: any = {
    shippingInfo: {
      country: "Україна",
      region: "",
      city: "",
      postalCode: "",
      postService: { id: null, name: "" },
    },
    shippingInfoValid:false
  };
  
  const shippingInfoSlice = createSlice({
    name: "shippingInfo",
    initialState,
    reducers: {
      updateShippingInfo(state, action) {
        const { country, region, city, postalCode, postService } = action.payload;
      state.shippingInfo = {
        country,
        region,
        city,
        postalCode,
        postService: postService || { id: null, name: "" },
      };
      state.shippingInfoValid=true;
      },
      shippingInfoNotValid(state) {
        state.shippingInfoValid = false;
      },
    },
  });
  
  export const { updateShippingInfo,shippingInfoNotValid } = shippingInfoSlice.actions;
  export default shippingInfoSlice.reducer;