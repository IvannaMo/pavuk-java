import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/home/Home";
import SignUp from "./pages/sign-up/SignUp";
import ProtectedUserRoute from "./pages/user-account/ProtectedUserRoute";
import "./App.css";
import UserAccount from "./pages/user-account/UserAccount";


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="/user-profile" element={<ProtectedUserRoute><UserAccount /></ProtectedUserRoute>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;