import * as yup from "yup";


const passwordMinValue = 8;

const yupSignInSchema = yup.object({
  email: yup
    .string()
    .email("Введіть коректний email")
    .required("Введіть email"),
  password: yup
    .string()
    .min(passwordMinValue, `Пароль не може бути менше ${passwordMinValue} символів`)
    .required("Введіть пароль"),
});

export default yupSignInSchema;