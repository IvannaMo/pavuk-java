import { ErrorMessage, Field, Formik } from "formik";
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { editUser } from "../../state/users/users-slice";
import { hideSignInForm } from "../../state/ui/ui-slice";
import Checkbox from "../common/Checkbox";
import DatePicker from "../common/DatePicker";
import UserType from "../../types/user-type";
import "./EditUserForm.css";


interface EditUserFormProps {
  userId: string | undefined;
}

function EditUserForm({ userId }: EditUserFormProps) {
  const [isFormValid, setIsFormValid] = useState(false);
  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const signUpError = useSelector((state: RootState) => state.users.signUpError);
  const currentUser = useSelector((state: RootState) => state.users.currentUser);
  const effectRan = useRef(false);
  const users = useSelector((state: RootState) => state.users.users);
  const user = currentUser.id == userId ? currentUser : users.find((user: UserType) => user.id === userId);

  useEffect(() => {
    if (effectRan.current === false) {
      dispatch(hideSignInForm());

      return () => {
        effectRan.current = true;
      }
    }
  }, []);

  const editHandler = async (data: any) => {
    const { password, confirmPassword, ...rest } = data;
    const tempUser = { ...user, ...rest };
    
    const resultAction = await dispatch(editUser(tempUser));
    if (editUser.fulfilled.match(resultAction)) {
      navigate("/user-account");
    }
  };

  return (
    <section className="edit-user-form-section pt-11 pb-14">
      <Formik 
        initialValues={{
          firstName: user.firstName,
          lastName: user.lastName,
          dateOfBirth: new Date(user.dateOfBirth),
          phone: user.phone,
          email: user.email,
          newsletterSubscription: user.newsletterSubscription,
        }}
        validationOnInput
        onSubmit={editHandler}
      >
        {({values, errors, touched, isValid, handleSubmit}) => {
          setIsFormValid(isValid);

          return (
            <form onSubmit={handleSubmit} className="edit-user-form px-16 pt-9 pb-12 w-6/12">
              <h2 className="edit-user-form-heading mb-4 w-full text-center text-3xl">Редагування користувача</h2>

              {signUpError && <p className="edit-user-form-field-error mt-3 mb-2 text-md">{signUpError}</p>}

              <div className="w-full">
                <label className="edit-user-form-label text-xl" htmlFor="firstName">Ім'я:</label>
                <Field
                  className="edit-user-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="firstName"
                  type="text"
                  id="name"
                />
                <ErrorMessage
                  className="edit-user-form-field-error mt-1 text-md"
                  name="firstName"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-user-form-label text-xl" htmlFor="lastName">Прізвище:</label>
                <Field
                  className="edit-user-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="lastName"
                  type="text"
                  id="lastName"
                />
                <ErrorMessage
                  className="edit-user-form-field-error mt-1 text-md"
                  name="lastName"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-user-form-label text-xl" htmlFor="dateOfBirth">Дата народження:</label>
                <DatePicker
                  className="edit-user-form-field mt-2 px-3 py-1 w-full text-lg"
                  name="dateOfBirth"
                  id="dateOfBirth"
                />
                <ErrorMessage
                  className="edit-user-form-field-error mt-1 text-md"
                  name="dateOfBirth"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-user-form-label text-xl" htmlFor="phone">Телефон:</label>
                <Field
                  className="edit-user-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="phone"
                  type="text"
                  id="phone"
                />
                <ErrorMessage
                  className="edit-user-form-field-error mt-1 text-md"
                  name="phone"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label className="edit-user-form-label text-xl" htmlFor="email">Email:</label>
                <Field
                  className="edit-user-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="email"
                  type="text"
                  id="email"
                />
                <ErrorMessage
                  className="edit-user-form-field-error mt-1 text-md"
                  name="email"
                  component="div"
                />
              </div>

              <div className="flex gap-2 mt-3 w-full">
                <Checkbox name="newsletterSubscription"/>
                <label className="edit-user-form-label text-md">
                  Отримувати повідомлення про новинки, знижки, акції
                </label>
              </div>

              <button className={`${isFormValid ? "edit-user-form-button" : "edit-user-form-button-disabled"} mx-auto mt-10 px-8 py-3 w-9/12 text-xl`} type="submit" disabled={!isFormValid}>
                Редагувати
              </button>
            </form>
          );
        }}
      </Formik>
    </section>
  );
}

export default EditUserForm;