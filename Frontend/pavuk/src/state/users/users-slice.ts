import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import UserType from "../../types/user-type";


const initialState: any = {
  users: [],
  signInError: null,
  signUpError: null,
  isAuthenticated: false,
  currentUser: null
};


export const getUsers = createAsyncThunk(
  "users/getUsers",
  async (_, { rejectWithValue }) => {
    try {
      const res = await axios.get(
        import.meta.env.VITE_PATH_TO_SERVER + "users"
      );
      console.log(res.data);

      return res.data as UserType[];
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const signInUser = createAsyncThunk(
  "users/signInUser",
  async (data: UserType, { rejectWithValue }) => {
    try {
      const response = await axios.get(
        `${import.meta.env.VITE_PATH_TO_SERVER}users?email=${data.email}&password=${data.password}`
      );

      if (!response.data[0]) {
        return rejectWithValue("Неправильний email або пароль");
      }
      return response.data[0];
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const signUpUser = createAsyncThunk(
  "users/signUpUser",
  async (data: UserType, { rejectWithValue }) => {
    try {
      const response = await axios.post(
        import.meta.env.VITE_PATH_TO_SERVER + "users",
        data
      );

      if (response.status === 201) {
        return response.data as UserType;
      }
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const editUser = createAsyncThunk(
  "users/editUser",
  async (editedUser: UserType, { rejectWithValue }) => {
    try {
      const res = await axios.put(
        `${import.meta.env.VITE_PATH_TO_SERVER}users/${editedUser.id}`, 
        editedUser
      );
      return res.data as UserType;
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const removeUser = createAsyncThunk(
  "users/removeUser",
  async (userId: string, { rejectWithValue }) => {
    try {
      await axios.delete(`${import.meta.env.VITE_PATH_TO_SERVER}users/${userId}`);
      return userId; 
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);


const usersSlice = createSlice({
  name: "users",
  initialState,
  reducers: {
    resetSignInError(state) {
      state.signInError = null;
    },
    loadUser(state) {
      const currentUser = localStorage.getItem("currentUser");
      if (currentUser) {
        state.isAuthenticated = true;
        state.currentUser = JSON.parse(currentUser);
      }
    },
    signOutUser(state) { 
      state.isAuthenticated = false;
      state.currentUser = null;
      localStorage.removeItem("currentUser");
    }
  },
  extraReducers: (builder) => {
    builder
    .addCase(getUsers.pending, (state) => {
      console.log("getUsers pending");
    })
    .addCase(getUsers.fulfilled, (state, action) => {
      console.log("getUsers success");
      state.users = action.payload;
    })
    .addCase(getUsers.rejected, (state, action) => {
      console.log("getUsers rejected");
    })
    .addCase(signInUser.pending, (state) => {
      console.log("signInUser pending");
    })
    .addCase(signInUser.fulfilled, (state, action) => {
      console.log("signInUser success");
      state.signInError = null;
      // state.isAuthenticated = true;
      // state.currentUser = action.payload;
      // localStorage.setItem("currentUser", JSON.stringify(action.payload));
    })
    .addCase(signInUser.rejected, (state, action) => {
      console.log("signInUser rejected");
      state.signInError = action.payload as string;
    })
    .addCase(signUpUser.pending, (state) => {
      console.log("signUpUser pending");
    })
    .addCase(signUpUser.fulfilled, (state, action) => {
      console.log("signUpUser success");
      // state.isAuthenticated = true;
      // state.currentUser = action.payload;
      // state.users.push(action.payload);
    })
    .addCase(signUpUser.rejected, (state, action) => {
      console.log("signUpUser rejected");
    })
    .addCase(editUser.pending, (state) => {
      console.log("editUser pending");
    })
    .addCase(editUser.fulfilled, (state, action) => {
      console.log("editUser success");
      const updatedUser = action.payload;

      state.users = state.users.map((user: UserType) =>
        user.id === updatedUser.id ? updatedUser : user
      );

      if (state.currentUser.id === updatedUser.id) {
        state.currentUser = updatedUser;
        localStorage.setItem("currentUser", JSON.stringify(updatedUser));
      }
    })
    .addCase(editUser.rejected, (state, action) => {
      console.log("editUser rejected");
    })
    .addCase(removeUser.pending, (state) => {
      console.log("removeUser pending");
    })
    .addCase(removeUser.fulfilled, (state, action) => {
      console.log("removeUser success");
      state.users = state.users.filter((user: UserType) => user.id !== action.payload);
    })
    .addCase(removeUser.rejected, (state, action) => {
      console.log("removeUser rejected");
    });
  }
}); 

export const { resetSignInError, loadUser, signOutUser } = usersSlice.actions;
export default usersSlice.reducer;