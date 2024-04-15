import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Img from '../../../components/layout/ui/img';
// import { useSelector } from 'react-redux';
import {
  Avatar,
  Box,
  Button,
  Container,
  CssBaseline,
  Grid,
  Typography,
} from '@mui/material';
import LockIcon from '@mui/icons-material/Lock';
import TextFieldComponent from '../../../components/layout/ui/text-field-component';

const UserLoginForm = () => {
  const navigate = useNavigate();

  const loginUser = (userLoginData) => {
    console.log(userLoginData);
    navigate('/events');
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);

    const username = formData.get('username');
    const password = formData.get('password');

    const userLoginData = {
      username,
      password,
    };

    loginUser(userLoginData);
  };

  return (
    <Container component="main" maxWidth="xs" bgcolor="0087ac">
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Img
          src="../../assests/BB_logo.png"
          alt="Bill Basher Logo"
          sx={{
            aspectRatio: '1.42',
            width: 1 / 2,
            marginLeft: 'auto',
            marginRight: 'auto',
          }}
        />
        <Avatar sx={{ m: 1, bgcolor: 'primary.main' }}>
          <LockIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Login
        </Typography>
        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <TextFieldComponent
              defaultValue={''}
              required={true}
              label={'Username'}
              name={'username'}
              type={'text'}
            />
            <TextFieldComponent
              defaultValue={''}
              required={true}
              label={'Password'}
              name={'password'}
              type={'password'}
            />
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Login
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link to="/register" style={{ color: '#000000' }}>
                <Typography variant="body1">
                  New to Bill Basher? Register here
                </Typography>
              </Link>
            </Grid>
          </Grid>
          {/* {errorMessage && <Alert severity="error">{errorMessage}</Alert>} */}
        </Box>
      </Box>
    </Container>
  );
};

export default UserLoginForm;
