import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import UserLoginPage from './pages/auth/login/user-login-page';
import UserRegistrationPage from './pages/auth/register/user-registration-page';
import { CssBaseline, ThemeProvider } from '@mui/material';
import theme from './theme/theme';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<UserLoginPage />} />
            <Route path="/register" element={<UserRegistrationPage />} />
          </Routes>
        </BrowserRouter>
      </CssBaseline>
    </ThemeProvider>
  );
}

export default App;
