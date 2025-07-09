import { createSlice } from "@reduxjs/toolkit";


const initialState: any = {
  isSignInFormVisible: false,
  isShippingFormVisible:false
};


const uiSlice = createSlice({
  name: "ui",
  initialState,
  reducers: {
    showSignInForm(state) {
      state.isSignInFormVisible = true;
    },
    hideSignInForm(state) {
      state.isSignInFormVisible = false;
    },
    showShippingInfoForm(state) {
      state.isShippingFormVisible = true;
    },
    hideShippingInfoForm(state) {
      state.isShippingFormVisible = false;
    }
  }
}); 

export const { showSignInForm, hideSignInForm, showShippingInfoForm, hideShippingInfoForm } = uiSlice.actions;
export default uiSlice.reducer;