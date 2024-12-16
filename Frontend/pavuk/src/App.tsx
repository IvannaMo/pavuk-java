import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/home/Home";
import ClothingItems from "./pages/clothing-items/ClothingItems";
import SignUp from "./pages/sign-up/SignUp";
import ProtectedUserRoute from "./pages/user-account/ProtectedUserRoute";
import UserAccount from "./pages/user-account/UserAccount";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { checkAuthentication } from "./state/users/users-slice";
import { AppDispatch } from "./state/store";
import EditUser from "./pages/user-account/EditUser";
import ClothingItemCustomization from "./pages/clothing-items/ClothingItemCustomization";
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
        <Route path="/clothing-items/customization/:productId" element={<ProtectedUserRoute><ClothingItemCustomization /></ProtectedUserRoute>} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="/user-account" element={<ProtectedUserRoute><UserAccount /></ProtectedUserRoute>} />
        <Route path="/edit-user/:userId" element={<ProtectedUserRoute><EditUser /></ProtectedUserRoute>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;