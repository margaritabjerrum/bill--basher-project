import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Img from '../../components/layout/ui/img';
// import { useSelector } from 'react-redux';
import { Box, Button, Container, CssBaseline, Grid } from '@mui/material';
import EventsListComponent from './events-list-component';

const EventsPage = () => {
  const navigate = useNavigate();

  return (
    <Container component="main" maxWidth="xs" sx={{ width: '100%' }}>
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
        <Box display="flex" justifyContent="flex-end" sx={{ width: '100%' }}>
          <Button
            variant="contained"
            sx={{ mt: 3, mb: 2, width: '50%', ml: 2 }}
          >
            Create new Event
          </Button>
        </Box>
        <Box sx={{ width: '100%', height: '100%' }}>
          <EventsListComponent />
        </Box>
      </Box>
    </Container>
  );
};

export default EventsPage;
