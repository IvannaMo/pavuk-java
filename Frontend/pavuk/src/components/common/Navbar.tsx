import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { showSignInForm } from "../../state/ui/ui-slice";
import { loadUser } from "../../state/users/users-slice";
import { useEffect } from "react";
import SignInForm from "../common/SignInForm";
import "./Navbar.css";


interface NavbarProps {
  productsLink: boolean;
}

function Navbar({ productsLink = true }: NavbarProps) {
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    dispatch(loadUser());
  }, [dispatch]);

  const isSignInFormVisible = useSelector((state: RootState) => {
    return state.ui.isSignInFormVisible;
  });

  const isAuthenticated = useSelector((state: RootState) => state.users.isAuthenticated);

  const signIn = () => {
    dispatch(showSignInForm());
  }
  
  return (
    <>
      <header>
        <nav className="navbar-container px-24 py-5">
          <Link className="-mt-2 mr-10 text-3xl" to="/">Pavuk</Link>
          <div className="navbar-link-container">
            <Link className="navbar-link px-1 text-lg" to="/">
              Головна
            </Link>
          </div>
          <ul className="navbar-list">
            <li className="mr-10 text-lg">UA</li>
            
            {isAuthenticated ? (
              <Link className="px-7 py-2 text-lg" to="/user-profile">
                Особистий кабінет
              </Link>
            ) : (
              <button className="navbar-list-button px-7 py-2 text-lg" onClick={signIn}>
                Вхід
              </button>
            )}
            {productsLink && (
              <Link className="navbar-list-button navbar-list-order-button ml-3 px-7 py-2 text-lg" to="/products">
                Cтворити замовлення
              </Link>
            )}
          </ul>
        </nav>
      </header>

      {isSignInFormVisible && <SignInForm/>}
    </>
  );
}

export default Navbar;