import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import UserType from "../../types/user-type";


const initialState: any = {
  users: [],
  signInError: null,
  signUpError: null,
  currentUser: null,
};


export const getUsers = createAsyncThunk(
  "users/getUsers",
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get(
        `${import.meta.env.VITE_SERVER_PATH}users`,
        { withCredentials: true }
      );

      if (response.status === 200) {
        return response.data as UserType[];
      }
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
      const response = await axios.post(
        `${import.meta.env.VITE_SERVER_PATH}users/sign-in`,
        data,
        { withCredentials: true }
      );

      if (response.status === 200) {
        return response.data as UserType;
      }
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const checkAuthentication = createAsyncThunk(
  "users/checkAuthentication",
  async (_, { rejectWithValue }) => {
    try {
      const response = await axios.get(
        `${import.meta.env.VITE_SERVER_PATH}users/current`, 
        { withCredentials: true }
      );

      return response.data as UserType;
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
        `${import.meta.env.VITE_SERVER_PATH}users/sign-up`,
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

export const editCurrentUser = createAsyncThunk(
  "users/editCurrentUser",
  async (editedCurrentUser: UserType, { rejectWithValue }) => {
    try {
      const response = await axios.put(
        `${import.meta.env.VITE_SERVER_PATH}users/current/${editedCurrentUser.id}`, 
        editedCurrentUser,
        { withCredentials: true }
      );

      if (response.status === 200) {
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
      const response = await axios.put(
        `${import.meta.env.VITE_SERVER_PATH}users/${editedUser.id}`, 
        editedUser,
        { withCredentials: true }
      );

      if (response.status === 200) {
        return response.data as UserType;
      }
    } 
    catch (error: any) {
      return rejectWithValue(error.message);
    }
  }
);

export const signOutUser = createAsyncThunk(
  "users/signOutUser",
  async (_, { rejectWithValue }) => {
    try {
      await axios.post(
        `${import.meta.env.VITE_SERVER_PATH}users/sign-out`, 
        {}, 
        { withCredentials: true }
      );
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
      const response = await axios.delete(
        `${import.meta.env.VITE_SERVER_PATH}users/${userId}`,
        { withCredentials: true }
      );

      if (response.status === 200) {
        return response.data as string;
      }
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
      state.currentUser = action.payload;
    })
    .addCase(signInUser.rejected, (state, action) => {
      console.log("signInUser rejected");
      state.signInError = action.payload as string;
      state.currentUser = null;
    })
    .addCase(checkAuthentication.fulfilled, (state, action) => {
      console.log("checkAuthentication success");
      state.currentUser = action.payload;
    })
    .addCase(checkAuthentication.pending, (state) => {
      console.log("checkAuthentication pending");
    })
    .addCase(checkAuthentication.rejected, (state) => {
      console.log("checkAuthentication rejected");
      state.currentUser = null;
    })
    .addCase(signUpUser.fulfilled, (state, action) => {
      console.log("signUpUser success");
      state.currentUser = action.payload;
      state.users.push(action.payload);
    })
    .addCase(signUpUser.pending, (state) => {
      console.log("signUpUser pending");
    })
    .addCase(signUpUser.rejected, (state, action) => {
      console.log("signUpUser rejected");
      state.currentUser = null;
    })
    .addCase(editCurrentUser.pending, (state) => {
      console.log("editCurrentUser pending");
    })
    .addCase(editCurrentUser.fulfilled, (state, action) => {
      console.log("editCurrentUser success");
      const updatedCurrentUser = action.payload;

      if (updatedCurrentUser) {
        state.users = state.users.map((user: UserType) =>
          user.id === updatedCurrentUser.id ? updatedCurrentUser : user
        );

        state.currentUser = updatedCurrentUser;
      }
    })
    .addCase(editCurrentUser.rejected, (state, action) => {
      console.log("editCurrentUser rejected");
    })
    .addCase(editUser.pending, (state) => {
      console.log("editUser pending");
    })
    .addCase(editUser.fulfilled, (state, action) => {
      console.log("editUser success");
      const updatedUser = action.payload;

      if (updatedUser) {
        state.users = state.users.map((user: UserType) =>
          user.id === updatedUser.id ? updatedUser : user
        );
      }
    })
    .addCase(editUser.rejected, (state, action) => {
      console.log("editUser rejected");
    })
    .addCase(signOutUser.fulfilled, (state) => {
      console.log("signOutUser success");
      state.currentUser = null;
    })
    .addCase(signOutUser.rejected, (state, action) => {
      console.log("signOutUser rejected");
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

export const { resetSignInError } = usersSlice.actions;
export default usersSlice.reducer;