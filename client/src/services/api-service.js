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

const getUsers = async () => {
  const data = await api.get('/users');
  return data;
};

const getUserEvents = async (userId) => {
  const data = await api.get(`/events/by-user/${userId}`);
  return data;
};

const createEvent = async (userId, eventName) => {
  const res = await api.post('/events', {
    userId: {
      userId: userId,
    },
    eventActive: true,
    eventName: eventName,
  });
  return res;
};

const addMembersToEvent = async (userId, eventId) => {
  const res = await api.post('/user-event/add', {
    userId: {
      userId,
    },
    eventId: {
      eventId,
    },
    total: 0,
  });
  return res;
};

const getEventById = async (eventId) => {
  const data = await api.get(`/events/${eventId}`);
  return data;
};

const getExpensesPerEvent = async (eventId) => {
  const data = await api.get(`/expenses/event/${eventId}`);
  return data;
};

const ApiService = {
  createUser,
  login,
  getUsers,

  getUserEvents,
  createEvent,
  addMembersToEvent,
  getEventById,
  getExpensesPerEvent,
  // updateUser,
};

export default ApiService;
