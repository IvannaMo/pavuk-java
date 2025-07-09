import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  modalVisible: false,
  modalTitle: "",
  modalMessage: "",
  modalButtons: [],
};

const modalSlice = createSlice({
  name: "modal",
  initialState,
  reducers: {
    showModal: (state, action) => {
      const { title, message, buttons } = action.payload;
      state.modalVisible = true;
      state.modalTitle = title;
      state.modalMessage = message;
      state.modalButtons = buttons;
    },
    hideModal: (state) => {
      state.modalVisible = false;
    },
  },
});

export const { showModal, hideModal } = modalSlice.actions;
export default modalSlice.reducer;