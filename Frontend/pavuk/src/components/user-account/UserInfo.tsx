import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { AppDispatch, RootState } from "../../state/store";
import { useState } from "react";
import { signOutUser } from "../../state/users/users-slice";
import userAccountDefaultPhoto from "../../assets/images/user-account-photos/user-account-default-photo.jpg";
import formatDate from "../../utils/formatDate";
import "./UserInfo.css";


function UserInfo() {
  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const currentUser = useSelector((state: RootState) => state.users.currentUser);

  const [selectedUserInfoTab, setSelectedUserInfoTab] = useState(0);

  const items = [
    {
      title: "Загальна інформація",
      content: (
        <div className="user-info-general-info px-16 pt-9 pb-12">
          <div className="flex gap-6">
            <img className="user-info-general-info-photo h-40" src={userAccountDefaultPhoto}/>
            <div className="py-4">
              <p>
                <span className="user-info-heading text-xl">Ім'я:</span> 
                <span className="user-info-data ml-3 text-xl">{currentUser.firstName}</span>
              </p>
              <p className="mt-1">
                <span className="user-info-heading text-xl">Прізвище:</span> 
                <span className="user-info-data ml-3 text-xl">{currentUser.lastName}</span>
              </p>
              <p className="mt-1">
                <span className="user-info-heading text-xl">Дата народження:</span> 
                <span className="user-info-data ml-3 text-xl">{formatDate(currentUser.dateOfBirth)}</span>
              </p>
              <p className="mt-1">
                <span className="user-info-heading text-xl">Телефон:</span> 
                <span className="user-info-data ml-3 text-xl">{currentUser.phone}</span>
              </p>
              <p className="mt-1">
                <span className="user-info-heading text-xl">Email:</span> 
                <span className="user-info-data ml-3 text-xl">{currentUser.email}</span>
              </p>
              <p className="mt-1">
                <span className="user-info-heading text-xl">Підписка на розсилку:</span> 
                <span className="user-info-data ml-3 text-xl">{currentUser.newsletterSubscription ? "+" : "-"}</span>
              </p>

              <p className="user-info-data mt-6 text-lg">
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Excepturi ipsa aliquam non adipisci eos consequuntur dolorum pariatur nobis minima, in magni itaque, inventore maiores ea aut totam velit dignissimos! Quasi ipsa in eligendi explicabo earum.
              </p>
              <div className="flex justify-end mt-8 w-full">
                <Link className="user-info-general-info-edit-user-link px-5 py-2 text-lg" to={`/edit-user/${currentUser.id}`}>
                  Редагувати інформацію
                </Link>
              </div>
            </div>
          </div>
        </div>
      ),
    },
    {
      title: "Список користувачів",
      content: (
        <div className="h-full w-full">
          {/* <UserList /> */}
        </div>
      ),
    },
    {
      title: "Список товарів",
      content: (
        <div className="h-full w-full">
          {/* <ProductList /> */}
        </div>
      ),
    },
    {
      title: "Список замовлень",
      content: (
        <div className="h-full w-full">
          {/* <OrderList /> */}
        </div>
      ),
    }
  ];

  const signOutHandler = () => {
    dispatch(signOutUser());
    navigate("/");
  };

  const filteredItems = currentUser.role.name === "Admin" ? items : items.filter((_, index: number) => index === 0);

  return (
    <section className="user-info-section">
      <>
        <div className="user-info-tabs px-5 py-5">
          {filteredItems.map((item: any, index: number) => (
            <button key={index} className={`${selectedUserInfoTab === index ? "user-info-tab-selected" : "user-info-tab"} px-6 py-2 min-w-64 text-start text-xl`} onClick={() => setSelectedUserInfoTab(index)}>
              {item.title}
            </button>
          ))}
          <div className="flex flex-col justify-end grow">
            <button className="user-info-tabs-button px-6 py-2 min-w-64 text-start text-xl" onClick={signOutHandler}>
              Вийти з акаунту
            </button>
          </div>
        </div>
        {items.map((item: any, index: number) => (
          <div key={index} className={`user-info ${selectedUserInfoTab === index ? "" : "hidden"} px-14 pt-11 pb-14`}>
            {item.content}
          </div>
        ))}
      </>
    </section>
  );
}

export default UserInfo;