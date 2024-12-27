import { useLocation, useNavigate } from "react-router-dom";
import ClothingItemType from "../../types/clothing-item-type";
import { createOrder } from "../../state/orders/orders-slice";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import "./ClothingItemEditor.css";


function ClothingItemEditor() {
  const dispatch = useDispatch<AppDispatch>();
  const location = useLocation();
  const navigate = useNavigate();
  const { clothingItem } = location.state as { clothingItem: ClothingItemType };
  const primaryImage = Array.from(clothingItem.images).find(image => image.isPrimary);
  const imageUrl = primaryImage ? primaryImage.url : Array.from(clothingItem.images)[0]?.url;
  const currentUser = useSelector((state: RootState) => state.users.currentUser);
  
  const createOrderHandler = async (data: any) => {
    const postService = { id: 1, name: "Нова пошта" };
    const shippingInfo = { country: "Україна", region: "Одеська область", city: "Одеса", postalCode: "65001", postService };
    const order = { user: currentUser, clothingItem, shippingInfo };

    const resultAction = await dispatch(createOrder(order));
    if (createOrder.fulfilled.match(resultAction)) {
      const createdOrder = resultAction.payload;
      navigate(`/orders/confirmation/${createdOrder!.id}`, { state: { order: createdOrder } });
    }
  };

  return (
    <>
      <section className="clothing-editor-section pt-11 pb-14">
        <div className="clothing-editor w-8/12 h-full">
          <div className="h-full aspect-square px-20 py-12">
            <img className="clothing-editor-image" src={`${import.meta.env.VITE_SERVER_PATH}${imageUrl}`} alt={clothingItem.name}/>
          </div>

          <div className="pr-16 pt-9 pb-12 flex-1"> 
            <h4 className="clothing-editor-heading text-2xl">{clothingItem.name}</h4>
            <p className="clothing-editor-subtitle text-lg">{clothingItem.description}</p>
            <p className="clothing-editor-price mt-2">
              <span className="text-xl">{clothingItem.price}</span>
              <span className="clothing-editor-price-currency text-md">₴</span>
            </p>

            <p className="clothing-gallery-item-info mt-5 text-lg">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Nam repudiandae dolorum numquam consequuntur, doloribus et aspernatur ipsam enim, itaque adipisci in, temporibus veritatis architecto porro ipsa voluptatibus aperiam nulla consectetur?
            </p>

            <div className="flex justify-end mt-7">
              <button className="clothing-editor-button text-lg px-8 py-3" onClick={createOrderHandler}>
                Підтвердити
              </button>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default ClothingItemEditor;