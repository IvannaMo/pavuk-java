import RoleType from "./role-type";


type UserType = {
  id?: string,
  firstName: string,
  lastName: string,
  dateOfBirth: string,
  phone: string,
  email: string,
  newsletterSubscription: boolean,
  role: RoleType,
  status: "ONLINE" | "IDLE" | "OFFLINE" | "BANNED" | "REMOVED",
  registrationDate: string,
  onlineDate?: string,
};

export default UserType;