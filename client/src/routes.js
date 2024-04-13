import { useRoutes } from 'react-router-dom';
import UserLoginPage from './pages/auth/user-login-page';
import UserRegistrationPage from './pages/auth/register/user-registration-page';

export const AppRouter = () => {
  const elements = useRoutes([
    {
      path: '/',
      element: <UserLoginPage />,
    },
    {
      path: '/register',
      element: <UserRegistrationPage />,
    },
  ]);

  return elements;
};