import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { useEffect, useRef } from "react";
import { getClothingItems, removeClothingItem } from "../../state/clothing-items/clothing-items-slice";
import { sliceClothingItemName, sliceClothingItemDescription } from "../../utils/slice";
import { Link, useNavigate } from "react-router-dom";
import AdminClothingItemType from "../../types/admin-clothing-item-type";
import "./ClothingItemList.css";


function ClothingItemList() {
  const dispatch = useDispatch<AppDispatch>();
  const clothingItems: Array<AdminClothingItemType> = useSelector((state: RootState) => state.clothingItems.clothingItems);
  const navigate = useNavigate();
  const effectRan = useRef(false);

  useEffect(() => {
    if (effectRan.current === false) {
      dispatch(getClothingItems());
      
      return () => {
        effectRan.current = true;
      }
    }
  }, []);

  const editClothingItemHandler = async (clothingItemId: string) => {
    navigate(`/edit-clothing-item/${clothingItemId}`, { state: { clothingItems } });
  };

  const removeClothingItemHandler = async (clothingItemId: string) => {
    await dispatch(removeClothingItem(clothingItemId));
    dispatch(getClothingItems());
  };

  return (
    <>
      <Link className="clothing-list-create-clothing-link px-5 py-2 text-lg" to="/create-clothing-item">
        Створити товар
      </Link>
      <div className="clothing-list-container mt-5 max-h-96">
        <table className="clothing-list w-full">
          <thead className="clothing-list-thead">
            <tr>
              <th className="clothing-list-th px-6 py-2 text-left text-sm">ID</th>
              <th className="clothing-list-th px-6 py-2 text-left text-sm">Категорія</th>
              <th className="clothing-list-th px-6 py-2 text-left text-sm w-1/6">Назва</th>
              <th className="clothing-list-th px-6 py-2 text-left text-sm w-2/3">Опис</th>
              <th className="clothing-list-th px-6 py-2 text-left text-sm">Ціна</th>
              <th className="clothing-list-th px-6 py-2 text-left text-sm w-1/4">Статус</th>

              <th className="pl-8 py-5"></th>
              <th className="pl-1.5 pr-5 py-5"></th>
              </tr>
          </thead>
          <tbody>
            {clothingItems?.map((clothingItem: AdminClothingItemType) => {
              const clothingItemListTr = clothingItem.status !== "REMOVED" ? "clothing-list-tr" : "clothing-list-tr-removed";
              return (
                <tr key={clothingItem.id} className={clothingItemListTr}>
                  <td className="clothing-list-td px-6 py-2 text-sm">{clothingItem.id}</td>
                  <td className="clothing-list-td px-6 py-2 text-sm">{clothingItem.category.name}</td>
                  <td className="clothing-list-td px-6 py-2 text-sm w-1/6">{sliceClothingItemName(clothingItem.name)}</td>
                  <td className="clothing-list-td px-6 py-2 text-sm w-2/3">{sliceClothingItemDescription(clothingItem.description)}</td>
                  <td className="clothing-list-td px-6 py-2 text-sm">{clothingItem.price}</td>
                  <td className="clothing-list-td px-6 py-2 text-sm w-1/4">{clothingItem.status}</td>

                  {clothingItem.status !== "REMOVED" && (
                    <>
                      <td className="clothing-list-td pl-8">
                        <button className="clothing-list-edit-product-button px-1 py-1 w-9 text-sm" onClick={() => editClothingItemHandler(clothingItem.id)}>✎</button>
                      </td>
                      <td className="clothing-list-td pl-1.5 pr-5">
                        <button className="clothing-list-remove-product-button px-1 py-1 w-9 text-sm" onClick={() => removeClothingItemHandler(clothingItem.id)}>✖</button>
                      </td>
                    </>
                  )}
                </tr>
              );
            })}
          </tbody>
        </table>   
      </div>  
    </>
  );
}

export default ClothingItemList;