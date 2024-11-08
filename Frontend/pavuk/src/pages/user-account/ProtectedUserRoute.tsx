import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import { RootState } from "../../state/store";


function ProtectedUserRoute({ children }: { children: JSX.Element }) {
  const isAuthenticated = useSelector((state: RootState) => state.users.isAuthenticated);

  return isAuthenticated ? children : <Navigate to="/sign-up" />;
}

export default ProtectedUserRoute;