import { ErrorMessage, Field, Formik } from "formik";
import { useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { resetSignInError, signInUser } from "../../state/users/users-slice";
import { hideSignInForm } from "../../state/ui/ui-slice";
import yupSignInSchema from "../../schemes/yup-sign-in-schema";
import closeButton from "../../assets/icons/close-button.svg";
import "./SignInForm.css";


function SignIn() {
  const [isFormValid, setIsFormValid] = useState(false);
  const dispatch = useDispatch<AppDispatch>();
  const signInError = useSelector((state: RootState) => state.users.signInError);
  const effectRan = useRef(false);

  useEffect(() => {
    if (effectRan.current === false) {
      dispatch(resetSignInError());

      return () => {
        effectRan.current = true;
      }
    }
  }, []);
  
  const signInHandler = async (data: any) => {
    const resultAction = await dispatch(signInUser(data));
    if (signInUser.fulfilled.match(resultAction)) {
      dispatch(hideSignInForm());
    }
  };

  const closeForm = () => {
    dispatch(hideSignInForm());
  };

  const signUp = (e: React.MouseEvent<HTMLAnchorElement>) => {
    if (location.pathname === "/sign-up") {
      e.preventDefault();
      window.location.reload();
    }
  };
  
  return (
    <section className="sign-in-form-section pt-11 pb-14">
      <Formik 
        initialValues={{
          email: "",
          password: "",
        }}
        validationSchema={yupSignInSchema}
        validationOnInput
        onSubmit={signInHandler} 
      >
        {({values, errors, touched, isValid, handleSubmit}) => {
          useEffect(() => {
            setIsFormValid(isValid);
          }, [isValid]); 

          return (
            <form onSubmit={handleSubmit} className="sign-in-form px-16 pt-9 pb-11">
              <button className="sign-in-form-close-button top-5 right-5" style={{ backgroundImage: `url(${closeButton})` }} onClick={closeForm}></button>
              
              <h2 className="sign-in-form-heading mb-4 w-full text-center text-3xl">Вхід</h2>

              {signInError && <p className="sign-in-form-field-error mt-3 mb-2 text-md">{signInError}</p>}

              <div className="w-full">
                <label className="sign-in-form-label text-xl" htmlFor="email">Email:</label>
                <Field
                  className="sign-in-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="email"
                  type="text"
                  id="email"
                />
                <ErrorMessage
                  className="sign-in-form-field-error mt-1 text-md"
                  name="email"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="sign-in-form-label text-xl" htmlFor="password">Пароль:</label>
                <Field
                  className="sign-in-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="password"
                  type="password"
                  id="password"
                />
                <ErrorMessage
                  className="sign-in-form-field-error mt-1 text-md"
                  name="password"
                  component="div"
                />
              </div>

              <button className={`${isFormValid ? "sign-in-form-button" : "sign-in-form-button-disabled"} mx-auto mt-10 px-8 py-3 w-9/12 text-xl`} type="submit" disabled={!isFormValid}>
                Увійти
              </button>

              <p className="sign-in-form-paragraph mt-4 w-full text-center text-md">
                Немає акаунту?
                <Link className="sign-in-form-link ml-2" to="/sign-up" onClick={signUp}>
                  Зареєструватися
                </Link>
              </p>
            </form>
          );
        }}
      </Formik>
    </section>
  );
}

export default SignIn;