import React from 'react';
import { useNavigate } from 'react-router-dom';
import ApiService from '../../../services/api-service';
import {
  Avatar,
  Alert,
  Box,
  Button,
  Container,
  CssBaseline,
  Grid,
  Typography,
} from '@mui/material';
import TextFieldComponent from '../../../components/layout/ui/text-field-component';
import Img from '../../../components/layout/ui/img';
import LockIcon from '@mui/icons-material/Lock';

const UserRegistrationForm = () => {
  const navigate = useNavigate();
  const [error, setError] = React.useState('');

  const createUser = async (data) => {
    console.log(data)
    const res = await ApiService.createUser(data);
    console.log(res);
    if (!res.data.success) {
      setError(res.data);
    } else {
      console.log('Registration is SUCCESSFULL');
      navigate('/');
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);

    const username = formData.get('username');
    const name = formData.get('name');
    const surname = formData.get('surname');
    const email = formData.get('email');
    const password = formData.get('password');
    const password2 = formData.get('password2');

    if (password !== password2) {
      setError('Passwords do not match');
    } else {
      setError('');
      const userRegistrationData = {
        username,
        name,
        surname,
        email,
        password,
      };

      createUser(userRegistrationData);
    }
  };

  return (
    <Container component="main" maxWidth="xs">
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
          Register
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
              label={'First Name'}
              name={'name'}
              type={'text'}
            />
            <TextFieldComponent
              defaultValue={''}
              required={true}
              label={'Last Name'}
              name={'surname'}
              type={'text'}
            />
            <TextFieldComponent
              defaultValue={''}
              required={true}
              label={'Email'}
              name={'email'}
              type={'text'}
            />
            <TextFieldComponent
              defaultValue={''}
              required={true}
              label={'Password'}
              name={'password'}
              type={'password'}
            />
            <TextFieldComponent
              defaultValue={''}
              required={true}
              label={'Repeat Password'}
              name={'password2'}
              type={'password'}
            />
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Register
          </Button>
          {error && (
            <Alert severity="error" variant="filled">
              {error}
            </Alert>
          )}
        </Box>
      </Box>
    </Container>
  );
};

export default UserRegistrationForm;
