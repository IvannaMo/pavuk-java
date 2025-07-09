import * as yup from "yup";

const yupShippingInfoSchema = yup.object({
  country: yup
    .string()
    .required("Вкажіть країну"),
  region: yup
    .string()
    .required("Вкажіть область"),
  city: yup
    .string()
    .required("Вкажіть місто"),
  postalCode: yup
    .string()
    .matches(/^\d{5}$/, "Індекс має складатись з 5 цифр")
    .required("Вкажіть поштовий індекс"),
  postService: yup
    .string()
    .required("Оберіть поштову службу"),
});

export default yupShippingInfoSchema;