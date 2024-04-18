import React from 'react';
// import { Link, useNavigate } from 'react-router-dom';
import Img from '../../components/layout/ui/img';
import { Box, Button, Container, CssBaseline, Typography } from '@mui/material';
import NavBar from '../../components/layout/navbar/navbar';
import { useSelector } from 'react-redux';

const UserAccountPage = () => {
  const user = useSelector((state) => state.user.user);
  console.log(user);
  return (
    <>
      <Container component="main" maxWidth="xs" sx={{ width: '100%' }}>
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            height: '70vh',
            textAlign: 'center',
          }}
        >
          <Img
            src="../../../assests/BB_logo.png"
            alt="Bill Basher Logo"
            sx={{
              aspectRatio: '1.42',
              width: 1 / 2,
              marginLeft: 'auto',
              marginRight: 'auto',
            }}
          />
          <Box sx={{ width: '100%', height: '100%', margin: 'auto' }}>
            <Typography variant="h5" component="h2">
              Username: {user.username}{' '}
            </Typography>
            <Typography variant="h5" component="h2">
              First name: {user.name}{' '}
            </Typography>
            <Typography variant="h5" component="h2">
              Last name: {user.surname}{' '}
            </Typography>
            <Typography variant="h5" component="h2">
              Email: {user.email}{' '}
            </Typography>
          </Box>
          <Box display="flex" justifyContent="flex-end" sx={{ width: '100%' }}>
            <Button variant="contained" sx={{ mt: 3, mb: 2, width: '100%' }}>
              Edit
            </Button>
          </Box>
        </Box>

        <NavBar />
      </Container>
    </>
  );
};

export default UserAccountPage;
