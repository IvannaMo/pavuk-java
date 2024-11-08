import * as yup from "yup";


const passwordMinValue = 8;

const yupSignUpSchema = yup.object({
  name: yup
    .string()
    .matches(/^[A-Za-zА-Яа-яЁёҐґЄєІіЇї]+$/, "Ім'я повинне містити тільки букви")
    .required("Введіть ім'я"),
  surname: yup
    .string()
    .matches(/^[A-Za-zА-Яа-яЁёҐґЄєІіЇї]+$/, "Прізвище повинне містити тільки букви")
    .required("Введіть прізвище"),
  birthdate: yup
    .date()
    .nullable()
    .required("Введіть дату народження")
    .max(new Date(), "Введіть коректну дату народження"),
  phoneNumber: yup
    .string()
    .matches(/^(380\d{9}|\+380\d{9}|\d{10}|\+\d{10})$/, "Введіть коректний номер телефону")
    .required("Введіть номер телефону"),
  email: yup
    .string()
    .email("Введіть коректний email")
    .required("Введіть email"),
  password: yup
    .string()
    .min(passwordMinValue, `Пароль не може бути менше ${passwordMinValue} символів`)
    .required("Введіть пароль"),
  confirmPassword: yup
    .string()
    .required("Порожнє поле")
    .oneOf([yup.ref("password"), "Паролі не однакові"]),
});

export default yupSignUpSchema;