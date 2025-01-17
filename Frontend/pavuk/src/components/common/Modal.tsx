import React from "react";
import { useDispatch, useSelector } from "react-redux";
import "./Modal.css";
import { useNavigate } from "react-router-dom";
import { removeUser, signOutUser } from "../../state/users/users-slice";
import { RootState } from "../../state/store";
import { hideModal } from "../../state/ui/modal-slice";
import closeButton from "../../assets/icons/close-button.svg";
import { getClothingItems, removeClothingItem } from "../../state/clothing-items/clothing-items-slice";

export const Modal = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { modalVisible, modalTitle, modalMessage, modalButtons } = useSelector(
    (state:RootState) => state.modal
  );

  if (!modalVisible) return null;

  const handleAction = (actionType: string, actionPayload?: string) => {
    if (actionType === "signOut") {
      dispatch(signOutUser() as any); 
      navigate("/");
      dispatch(hideModal());
    } else if (actionType === "cancel") {
      dispatch(hideModal());
    } else if (actionType === "removeClothingItem" && actionPayload) {
      dispatch(removeClothingItem(actionPayload) as any);
      dispatch(hideModal()); 
    } else if (actionType === "removeUser" && actionPayload) {
      dispatch(removeUser(actionPayload) as any);
      dispatch(hideModal()); 
    }
    
  };

  return (
    <div className="modal-overlay">
      <div className="modal">
        <button className="sign-in-form-close-button top-5 right-5" style={{ backgroundImage: `url(${closeButton})` }} onClick={() => dispatch(hideModal())}></button>
        <h2 className="modal-heading">{modalTitle}</h2>
        <p className="modal-message">{modalMessage}</p>
        <div className="modal-buttons">
          {modalButtons.map((button: any, index: any) => (
            <button
              key={index}
              className="modal-button mx-auto mt-2 px-8 py-3 w-9/12 "
              onClick={() => handleAction(button.actionType,button.actionPayload)}
            >
              {button.label}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
};