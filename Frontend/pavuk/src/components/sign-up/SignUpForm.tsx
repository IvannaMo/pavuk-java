import { ErrorMessage, Field, Formik } from "formik";
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { signUpUser } from "../../state/users/users-slice";
import { hideSignInForm } from "../../state/ui/ui-slice";
import yupSignUpSchema from "../../schemes/yup-sign-up-schema";
import DatePicker from "../common/DatePicker";
import Checkbox from "../common/Checkbox";
import "./SignUpForm.css";


function SignUpForm() {
  const [isFormValid, setIsFormValid] = useState(false);
  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const signUpError = useSelector((state: RootState) => state.users.signUpError);
  const effectRan = useRef(false);
  
  useEffect(() => {
    if (effectRan.current === false) {
      dispatch(hideSignInForm());

      return () => {
        effectRan.current = true;
      }
    }
  }, []);

  const signUpHandler = async (data: any) => {
    const { confirmPassword, ...userData } = data;
    
    const resultAction = await dispatch(signUpUser(userData));
    if (signUpUser.fulfilled.match(resultAction)) {
      navigate("/user-profile");
    }
  };

  return (
    <section className="sign-up-form-section pt-11 pb-14">
      <Formik 
        initialValues={{
          firstName: "",
          lastName: "",
          dateOfBirth: null,
          phone: "",
          email: "",
          newsletterSubscription: false,
          password: "",
          confirmPassword: "",
        }}
        validationSchema={yupSignUpSchema}
        validationOnInput
        onSubmit={signUpHandler} 
      >
        {({values, errors, touched, isValid, handleSubmit}) => {
          useEffect(() => {
            setIsFormValid(isValid);
          }, [isValid]); 

          return (
            <form onSubmit={handleSubmit} className="sign-up-form px-16 pt-9 pb-11 w-6/12">
              <h2 className="sign-up-form-heading mb-4 w-full text-center text-3xl">Реєстрація</h2>

              {signUpError && <p className="sign-up-form-field-error mt-3 mb-2 text-md">{signUpError}</p>}

              <div className="w-full">
                <label className="sign-up-form-label text-xl" htmlFor="firstName">Ім'я:</label>
                <Field
                  className="sign-up-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="firstName"
                  type="text"
                  id="firstName"
                />
                <ErrorMessage
                  className="sign-up-form-field-error mt-1 text-md"
                  name="firstName"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="sign-up-form-label text-xl" htmlFor="lastName">Прізвище:</label>
                <Field
                  className="sign-up-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="lastName"
                  type="text"
                  id="lastName"
                />
                <ErrorMessage
                  className="sign-up-form-field-error mt-1 text-md"
                  name="lastName"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="sign-up-form-label text-xl" htmlFor="dateOfBirth">Дата народження:</label>
                <DatePicker
                  className="sign-up-form-field mt-2 px-3 py-1 w-full text-lg"
                  name="dateOfBirth"
                  id="dateOfBirth"
                />
                <ErrorMessage
                  className="sign-up-form-field-error mt-1 text-md"
                  name="dateOfBirth"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="sign-up-form-label text-xl" htmlFor="phone">Телефон:</label>
                <Field
                  className="sign-up-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="phone"
                  type="text"
                  id="phone"
                />
                <ErrorMessage
                  className="sign-up-form-field-error mt-1 text-md"
                  name="phone"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="sign-up-form-label text-xl" htmlFor="email">Email:</label>
                <Field
                  className="sign-up-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="email"
                  type="text"
                  id="email"
                />
                <ErrorMessage
                  className="sign-up-form-field-error mt-1 text-md"
                  name="email"
                  component="div"
                />
              </div>

              <div className="flex gap-2 mt-3 w-full">
                <Checkbox name="newsletterSubscription"/>
                <label className="sign-up-form-label text-md">
                  Отримувати повідомлення про новинки, знижки, акції
                </label>
              </div>

              <div className="mt-6 w-full">
                <label className="sign-up-form-label text-xl" htmlFor="password">Пароль:</label>
                <Field
                  className="sign-up-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="password"
                  type="password"
                  id="password"
                />
                <ErrorMessage
                  className="sign-up-form-field-error mt-1 text-md"
                  name="password"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="sign-up-form-label text-xl" htmlFor="confirmPassword">Повтор пароля:</label>
                <Field
                  className="sign-up-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="confirmPassword"
                  type="password"
                  id="confirmPassword"
                />
                <ErrorMessage
                  className="sign-up-form-field-error mt-1 text-md"
                  name="confirmPassword"
                  component="div"
                />
              </div>

              <button className={`${isFormValid ? "sign-up-form-button" : "sign-up-form-button-disabled"} mx-auto mt-11 px-8 py-3 w-9/12 text-xl`} type="submit" disabled={!isFormValid}>
                Зареєструватися
              </button>
            </form>
          );
        }}
      </Formik>
    </section>
  );
}

export default SignUpForm;