import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../state/store";
import { useEffect, useRef } from "react";
import { checkAuthentication, getUsers, removeUser } from "../../state/users/users-slice";
import { Link, useNavigate } from "react-router-dom";
import AdminUserType from "../../types/admin-user-type";
import formatDate from "../../utils/formatDate";
import "./UserList.css";


function UserList() {
  const dispatch = useDispatch<AppDispatch>();
  const users: Array<AdminUserType> = useSelector((state: RootState) => state.users.users);
  const currentUser = useSelector((state: RootState) => state.users.currentUser);
  const navigate = useNavigate();
  const effectRan = useRef(false);

  useEffect(() => {
    if (effectRan.current === false) {
      dispatch(getUsers());
      
      return () => {
        effectRan.current = true;
      }
    }
  }, []);

  const editUserHandler = async (userId: string) => {
    navigate(`/edit-user/${userId}`, { state: { users } });
  };

  const removeUserHandler = async (userId: string) => {
    await dispatch(removeUser(userId));
    dispatch(getUsers());
  };

  const filteredUsers = users.filter((user) => user.id !== currentUser.id);

  return (
    <>
      <Link className="user-list-create-user-link px-5 py-2 text-lg" to="/create-user">
        Створити користувача
      </Link>
      <div className="user-list-container mt-5 max-h-96">
        <table className="user-list w-full">
          <thead className="user-list-thead">
            <tr>
              <th className="user-list-th px-8 py-2 text-left text-sm">ID</th>
              <th className="user-list-th px-8 py-2 text-left text-sm">Ім'я</th>
              <th className="user-list-th px-8 py-2 text-left text-sm">Прізвище</th>
              <th className="user-list-th px-8 py-2 text-left text-sm">Дата народження</th>
              <th className="user-list-th px-8 py-2 text-left text-sm">Телефон</th>
              <th className="user-list-th px-8 py-2 text-left text-sm">Email</th>
              <th className="user-list-th px-8 py-2 text-left text-sm">Підписка на розсилку</th>
              <th className="px-5 py-5"></th>
              <th className="px-5 py-5"></th>
              </tr>
          </thead>
          <tbody>
            {filteredUsers?.map((user: AdminUserType) => {
              const userListTr = user.status !== "REMOVED" ? "user-list-tr" : "user-list-tr-removed";
              return(
                <tr key={user.id} className={userListTr}>
                  <td className="user-list-td px-8 py-2 text-sm">{user.id}</td>
                  <td className="user-list-td px-8 py-2 text-sm">{user.firstName}</td>
                  <td className="user-list-td px-8 py-2 text-sm">{user.lastName}</td>
                  <td className="user-list-td px-8 py-2 text-sm">{formatDate(user.dateOfBirth)}</td>
                  <td className="user-list-td px-8 py-2 text-sm">{user.phone}</td>
                  <td className="user-list-td px-8 py-2 text-sm">{user.email}</td>
                  <td className="user-list-td px-8 py-2 text-sm">{user.newsletterSubscription ? "+" : "-"}</td>
                  
                  {user.status !== "REMOVED" && (
                    <>
                      <td className="user-list-td">
                        <button className="user-list-edit-user-button px-1 py-1 w-9 text-sm" onClick={() => editUserHandler(user.id)}>✎</button>
                      </td>
                      <td className="user-list-td">
                        <button className="user-list-remove-user-button ml-1 mr-8 px-1 py-1 w-9 text-sm" onClick={() => removeUserHandler(user.id)}>✖</button>
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

export default UserList;