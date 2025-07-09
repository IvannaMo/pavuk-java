import { ErrorMessage, Field, Formik } from "formik";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "../../state/store";
import {
  hideShippingInfoForm,
  showShippingInfoForm,
} from "../../state/ui/ui-slice";
import yupShippingInfoSchema from "../../schemes/yup-shipping-info-schema";
import closeButton from "../../assets/icons/close-button.svg";
import "./ShippingInfoForm.css";
import { updateShippingInfo } from "../../state/shipping-info/shipping-info-slice";

function ShippingInfoForm() {
  const [isFormValid, setIsFormValid] = useState(false);
  const dispatch = useDispatch<AppDispatch>();

  const closeForm = () => {
    dispatch(hideShippingInfoForm());
  };

  const submitHandler = (values: any) => {
    console.log("Form values:", values);

    const { postService, ...otherValues } = values;

    const postServiceObj = {
      id: postService === "1" ? 1 : postService === "2" ? 2 : null,
      name:
        postService === "1"
          ? "Нова пошта"
          : postService === "2"
          ? "Укрпошта"
          : "",
    };

    dispatch(
      updateShippingInfo({ ...otherValues, postService: postServiceObj })
    );

    dispatch(hideShippingInfoForm());
  };

  const ukraineRegions = [
    "Вінницька область",
    "Волинська область",
    "Дніпропетровська область",
    "Донецька область",
    "Житомирська область",
    "Закарпатська область",
    "Запорізька область",
    "Івано-Франківська область",
    "Київська область",
    "Кіровоградська область",
    "Луганська область",
    "Львівська область",
    "Миколаївська область",
    "Одеська область",
    "Полтавська область",
    "Рівненська область",
    "Сумська область",
    "Тернопільська область",
    "Харківська область",
    "Херсонська область",
    "Хмельницька область",
    "Черкаська область",
    "Чернівецька область",
    "Чернігівська область",
  ];

  return (
    <section className="shipping-info-form-section pt-11 pb-14">
      <Formik
        initialValues={{
          country: "Україна",
          region: "",
          city: "",
          postalCode: "",
          postService: "",
        }}
        validationSchema={yupShippingInfoSchema}
        validateOnInput
        onSubmit={submitHandler}
      >
        {({ isValid, handleSubmit }) => {
          useEffect(() => {
            setIsFormValid(isValid);
          }, [isValid]);

          return (
            <form
              onSubmit={handleSubmit}
              className="shipping-info-form px-16 pt-9 pb-11"
            >
              <button
                className="shipping-info-form-close-button top-5 right-5"
                style={{ backgroundImage: `url(${closeButton})` }}
                onClick={closeForm}
                type="button"
              ></button>

              <h2 className="shipping-info-form-heading mb-4 w-full text-center text-3xl">
                Інформація для доставки
              </h2>

              <div className="w-full">
                <label
                  className="shipping-info-form-label text-xl"
                  htmlFor="country"
                >
                  Країна:
                </label>
                <Field
                  className="shipping-info-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="country"
                  type="text"
                  id="country"
                  disabled
                />
                <ErrorMessage
                  className="shipping-info-form-field-error mt-1 text-md"
                  name="country"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label
                  className="shipping-info-form-label text-xl"
                  htmlFor="region"
                >
                  Область:
                </label>
                <Field
                  as="select"
                  className="shipping-info-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="region"
                  id="region"
                >
                  <option value="">Оберіть область</option>
                  {ukraineRegions.map((region) => (
                    <option key={region} value={region}>
                      {region}
                    </option>
                  ))}
                </Field>
                <ErrorMessage
                  className="shipping-info-form-field-error mt-1 text-md"
                  name="region"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label
                  className="shipping-info-form-label text-xl"
                  htmlFor="city"
                >
                  Місто:
                </label>
                <Field
                  className="shipping-info-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="city"
                  type="text"
                  id="city"
                />
                <ErrorMessage
                  className="shipping-info-form-field-error mt-1 text-md"
                  name="city"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label
                  className="shipping-info-form-label text-xl"
                  htmlFor="postalCode"
                >
                  Поштовий індекс:
                </label>
                <Field
                  className="shipping-info-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="postalCode"
                  type="text"
                  id="postalCode"
                />
                <ErrorMessage
                  className="shipping-info-form-field-error mt-1 text-md"
                  name="postalCode"
                  component="div"
                />
              </div>

              <div className="mt-4 w-full">
                <label
                  className="shipping-info-form-label text-xl"
                  htmlFor="postService"
                >
                  Поштова служба:
                </label>
                <Field
                  as="select"
                  className="shipping-info-form-field mt-2 px-3 py-1 min-w-96 text-lg"
                  name="postService"
                  id="postService"
                >
                  <option value="">Оберіть службу</option>
                  <option value="1">Нова пошта</option>
                  <option value="2">Укрпошта</option>
                </Field>
                <ErrorMessage
                  className="shipping-info-form-field-error mt-1 text-md"
                  name="postService"
                  component="div"
                />
              </div>

              <button
                className={`${
                  isFormValid
                    ? "shipping-info-form-button"
                    : "shipping-info-form-button-disabled"
                } mx-auto mt-10 px-8 py-3 w-9/12 text-xl`}
                type="submit"
                disabled={!isFormValid}
              >
                Зберегти
              </button>
            </form>
          );
        }}
      </Formik>
    </section>
  );
}

export default ShippingInfoForm;
