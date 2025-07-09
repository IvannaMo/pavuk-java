import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { checkAuthentication } from "./state/users/users-slice";
import { AppDispatch } from "./state/store";
import Home from "./pages/home/Home";
import ClothingItems from "./pages/clothing-items/ClothingItems";
import SignUp from "./pages/sign-up/SignUp";
import ProtectedAdminUserRoute from "./pages/user-account/ProtectedAdminUserRoute";
import ProtectedUserRoute from "./pages/user-account/ProtectedUserRoute";
import UserAccount from "./pages/user-account/UserAccount";
import EditCurrentUser from "./pages/user-account/EditCurrentUser";
import EditUser from "./pages/user-account/EditUser";
import ClothingItemCustomization from "./pages/clothing-items/ClothingItemCustomization";
import OrderConfirmation from "./pages/orders/OrderConfirmation";
import CreateUser from "./pages/user-account/CreateUser";
import CreateClothingItem from "./pages/user-account/CreateClothingItem";
import EditClothingItem from "./pages/user-account/EditClothingItem";
import "./App.css";


function App() {
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    dispatch(checkAuthentication());
  }, [dispatch]);
  
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/clothing-items" element={<ClothingItems />} />
        <Route path="/clothing-items/customization/:clothingItemId" element={<ProtectedUserRoute><ClothingItemCustomization /></ProtectedUserRoute>} />
        <Route path="/orders/confirmation/:orderId" element={<ProtectedUserRoute><OrderConfirmation /></ProtectedUserRoute>} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="/user-account" element={<ProtectedUserRoute><UserAccount /></ProtectedUserRoute>} />
        <Route path="/create-user" element={<ProtectedAdminUserRoute><CreateUser /></ProtectedAdminUserRoute>} />
        <Route path="/edit-user" element={<ProtectedUserRoute><EditCurrentUser /></ProtectedUserRoute>} />
        <Route path="/edit-user/:userId" element={<ProtectedAdminUserRoute><EditUser /></ProtectedAdminUserRoute>} />
        <Route path="/create-clothing-item" element={<ProtectedAdminUserRoute><CreateClothingItem /></ProtectedAdminUserRoute>} />
        <Route path="/edit-clothing-item/:clothingItemId" element={<ProtectedUserRoute><EditClothingItem /></ProtectedUserRoute>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;