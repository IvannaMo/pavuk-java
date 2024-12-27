import UserType from "./user-type";


type AdminUserType = Omit<UserType, "id"> & {
  id: string;
};

export default AdminUserType;