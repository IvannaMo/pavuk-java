import { createSlice } from "@reduxjs/toolkit";


const initialState: any = {
  isSignInFormVisible: false
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
    }
  }
}); 

export const { showSignInForm, hideSignInForm } = uiSlice.actions;
export default uiSlice.reducer;