import { useRoutes } from 'react-router-dom';
import UserLoginPage from './pages/auth/user-login-page';
import UserRegistrationPage from './pages/auth/register/user-registration-page';
import EventsPage from './pages/events/events-page';
import CreateEvent from './pages/events-page/events/create-event';
import UserAccountPage from './pages/user/user-account-page';

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
    {
      path: '/events',
      element: <EventsPage />,
    },
    {
      path: '/createevent',
      element: <CreateEvent />,
    },
    {
      path: '/useraccount',
      element: <UserAccountPage />,
    },
  ]);

  return elements;
};
