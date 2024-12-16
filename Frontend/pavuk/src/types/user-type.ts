import RoleType from "./role-type";
import TailorType from "./tailor-type";


type UserType = {
  id: string,
  firstName: string,
  lastName: string,
  dateOfBirth: string,
  phone: string,
  email: string,
  newsletterSubscription: boolean,
  role: RoleType;
  tailor?: TailorType | null;
  status: 'ONLINE' | 'IDLE' | 'OFFLINE' | 'BANNED',
  registrationDate: string;
  onlineDate?: string | null;
};

export default UserType;