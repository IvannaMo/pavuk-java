import { ErrorMessage, Field, Formik } from "formik";
import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { hideSignInForm } from "../../state/ui/ui-slice";
import { editClothingItem } from "../../state/clothing-items/clothing-items-slice";
import ClothingItemType from "../../types/clothing-item-type";
import AdminClothingItemType from "../../types/admin-clothing-item-type";
import "./EditClothingItemForm.css";


interface EditClothingItemFormProps {
  clothingItemId: string | undefined;
}

function EditClothingItemForm({ clothingItemId }: EditClothingItemFormProps) {
  const [isFormValid, setIsFormValid] = useState(false);
  const dispatch = useDispatch<AppDispatch>();
  const location = useLocation();
  const navigate = useNavigate();
  const effectRan = useRef(false);
  const { clothingItems } = location.state as { clothingItems: Array<AdminClothingItemType> };
  const clothingItem = clothingItems.find((product: ClothingItemType) => product.id === clothingItemId);
  
  useEffect(() => {
    if (effectRan.current === false) {
      dispatch(hideSignInForm());

      return () => {
        effectRan.current = true;
      }
    }
  }, []);

  const editHandler = async (data: any) => {
    const tempClothingItem = { ...clothingItem, ...data };
    
    const resultAction = await dispatch(editClothingItem(tempClothingItem));
    if (editClothingItem.fulfilled.match(resultAction)) {
      navigate("/user-account");
    }
  };

  return (
    <></>
    // <section className="edit-product-form-container pt-11 pb-14">
    //   <Formik 
    //     initialValues={{
    //       name: product.name,
    //       description: product.description,
    //       price: product.price,
    //       imageUrl: product.imageUrl
    //     }}
    //     validationOnInput
    //     onSubmit={editHandler} 
    //   >
    //     {({values, errors, touched, isValid, handleSubmit}) => {
    //       setIsFormValid(isValid);

    //       return (
    //         <form onSubmit={handleSubmit} className="edit-product-form px-16 pt-9 pb-12 w-6/12">
    //           <h2 className="edit-product-form-title mb-4 w-full text-center text-3xl">Редагування товару</h2>

    //           {signUpError && <p className="edit-product-form-field-error mt-3 mb-2 text-md">{signUpError}</p>}

    //           <div className="w-full">
    //             <label className="edit-product-form-label text-xl" htmlFor="name">Назва:</label>
    //             <Field
    //               className="edit-product-form-field mt-2 px-3 py-1 min-w-96 text-lg"
    //               name="name"
    //               type="text"
    //               id="name"
    //             />
    //             <ErrorMessage
    //               className="edit-product-form-field-error mt-1 text-md"
    //               name="name"
    //               component="div"
    //             />
    //           </div>

    //           <div className="mt-4 w-full">
    //             <label className="edit-product-form-label text-xl" htmlFor="description">Опис:</label>
    //             <Field
    //               className="edit-product-form-field mt-2 px-3 py-1 min-w-96 text-lg"
    //               name="description"
    //               type="text"
    //               id="description"
    //             />
    //             <ErrorMessage
    //               className="edit-product-form-field-error mt-1 text-md"
    //               name="description"
    //               component="div"
    //             />
    //           </div>

    //           <div className="mt-4 w-full">
    //             <label className="edit-product-form-label text-xl" htmlFor="price">Ціна:</label>
    //             <Field
    //               className="edit-product-form-field mt-2 px-3 py-1 min-w-96 text-lg"
    //               name="price"
    //               type="text"
    //               id="price"
    //             />
    //             <ErrorMessage
    //               className="edit-product-form-field-error mt-1 text-md"
    //               name="price"
    //               component="div"
    //             />
    //           </div>

    //           <div className="mt-4 w-full">
    //             <label className="edit-product-form-label text-xl" htmlFor="imageUrl">URL-адреса зображення:</label>
    //             <Field
    //               className="edit-product-form-field mt-2 px-3 py-1 min-w-96 text-lg"
    //               name="imageUrl"
    //               type="text"
    //               id="imageUrl"
    //             />
    //             <ErrorMessage
    //               className="edit-product-form-field-error mt-1 text-md"
    //               name="imageUrl"
    //               component="div"
    //             />
    //           </div>

    //           <button className={`${isFormValid ? "sign-in-form-sign-in-button" : "sign-in-form-sign-in-button-disabled"} mx-auto mt-10 px-8 py-3 w-9/12 text-xl`} type="submit" disabled={!isFormValid}>
    //             Редагувати
    //           </button>
    //         </form>
    //       );
    //     }}
    //   </Formik>
    // </section>
  );
}

export default EditClothingItemForm;