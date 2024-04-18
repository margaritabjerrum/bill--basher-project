import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  timeout: 5000,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
  },
});

const createUser = async (data) => {
  const res = await api.post('/register', {
    username: data.username,
    name: data.name,
    surname: data.surname,
    email: data.email,
    password: data.password,
  });
  return res;
};

const login = async (data) => {
  const res = await api.post('/login', {
    usernameOrEmail: data.usernameOrEmail,
    password: data.password,
  });
  return res;
};

// const updateUser = async (data) => {
//   const res = await api.post(`updateuser`, {
//     id: data.id,
//     username: data.username,
//     email: data.email,
//     picture: data.picture,
//     password: data.password,
//     password2: data.password2,
//   });

//   return res;
// };

// const getUsers = async () => {
//   const { data } = await api.get('/users');
//   return data;
// };

// const getUser = async (id) => {
//   const { data } = await api.get(`/users/${id}`);
//   return data;
// };

// const getUserByUsername = async (username) => {
//   const { data } = await api.post(`/username`, {
//     username: username.chattingWith,
//   });
//   return data;
// };

const ApiService = {
  createUser,
  login,
  // updateUser,

  // getUsers,
  // getUser,
  // getUserByUsername,
};

export default ApiService;
