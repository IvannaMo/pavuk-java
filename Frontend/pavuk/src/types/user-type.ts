type UserType = {
  id: string,
  name: string,
  surname: string,
  birthdate: string,
  phoneNumber: string,
  email: string,
  emailSubscription: boolean,
  password: string,
  confirmPassword: string,
  roles: []
};

export default UserType;