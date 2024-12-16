import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { showSignInForm } from "../../state/ui/ui-slice";
import SignIn from "../common/SignInForm";
import "./HomeNavbar.css";


function HomeNavbar() {
  const dispatch = useDispatch<AppDispatch>();
  
  const isSignInFormVisible = useSelector((state: RootState) => {
    return state.ui.isSignInFormVisible;
  });

  const currentUser = useSelector((state: RootState) => state.users.currentUser);

  const signIn = () => {
    dispatch(showSignInForm());
  }
  
  return (
    <>
      <header>
        <nav className="home-navbar-container px-24 py-5">
          <Link className="-mt-2 mr-10 text-3xl" to="/">Pavuk</Link>
          <div className="home-navbar-link-container">
            <Link className="home-navbar-link px-1 text-lg" to="/">
              Головна
            </Link>
          </div>
          <ul className="home-navbar-list">
            <li className="mr-10 text-lg">UA</li>

            {currentUser ? (
              <Link className="px-7 py-2 text-lg" to="/user-account">
                Особистий кабінет
              </Link>
            ) : (
              <button className="home-navbar-list-button px-7 py-2 text-lg" onClick={signIn}>
                Вхід
              </button>
            )}
          </ul>
        </nav>
      </header>

      {isSignInFormVisible && <SignIn/>}
    </>
  );
}
  
export default HomeNavbar;