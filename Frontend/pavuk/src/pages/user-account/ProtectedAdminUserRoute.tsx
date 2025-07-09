import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import { RootState } from "../../state/store";


function ProtectedAdminUserRoute({ children }: { children: JSX.Element }) {
  const currentUser = useSelector((state: RootState) => state.users.currentUser);
  const isLoading = useSelector((state: RootState) => state.users.currentUser === null);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return currentUser && (currentUser.role.name === "ROLE_ADMIN" ) ? children : <Navigate to="/sign-up" />;
}

export default ProtectedAdminUserRoute;