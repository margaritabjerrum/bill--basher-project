import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import UserLoginPage from './pages/auth/user-login-page';
import { CssBaseline, ThemeProvider } from '@mui/material';
import theme from './theme/theme';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline>
        <BrowserRouter>
          <Routes>
            {/* <Route path="/register" element={<UserRegistrationPage />}/> */}
            <Route path="/" element={<UserLoginPage />} />
          </Routes>
        </BrowserRouter>
      </CssBaseline>
    </ThemeProvider>
  );
}

export default App;
