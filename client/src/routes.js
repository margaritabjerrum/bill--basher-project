import { useRoutes } from 'react-router-dom';
import UserLoginPage from './pages/auth/user-login-page';

export const AppRouter = () => {
  const elements = useRoutes([
    {
      path: '/',
      element: <UserLoginPage />,
    },
    // {
    //   path: '/register',
    //   element: <ForgotPassword />
    // }
  ]);

  return elements;
};
