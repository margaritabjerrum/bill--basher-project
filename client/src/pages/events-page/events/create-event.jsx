import React, { useState } from 'react';
import {
  Button,
  Box,
  Container,
  CssBaseline,
  IconButton,
  Grid,
  Typography,
} from '@mui/material';
import Img from '../../../components/layout/ui/img';
import NavBar from '../../../components/layout/navbar/navbar';
import TextFieldComponent from '../../../components/layout/ui/text-field-component';
import { useNavigate } from 'react-router-dom';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import GroupAddIcon from '@mui/icons-material/GroupAdd';
import EventMembersListComponent from './event-members-list-component copy';

const CreateEvent = () => {
  const navigate = useNavigate();
  const [clicked, setClicked] = React.useState(false);
  const [checkedItems, setCheckedItems] = useState({});
  const userId = 2;

  const createEvent = (eventCreationData) => {
    console.log(eventCreationData);
    navigate('/event');
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);

    const eventName = formData.get('eventName');

    const eventCreationData = {
      eventName,
      userId,
      checkedItems, // Include checked items in event creation data
    };

    createEvent(eventCreationData);
  };

  const handleCheckedItemsChange = (items) => {
    setCheckedItems(items);
  };

  return (
    <Container component="main" maxWidth="xs" sx={{ width: '100%' }}>
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          height: '70vh',
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
        <Typography variant="h4" component="h1" sx={{ color: 'primary.dark' }}>
          Create new event
        </Typography>
        <Box
          component="form"
          noValidate
          onSubmit={handleSubmit}
          sx={{
            mt: 3,
            width: '100%',
            display: 'flex',
            flexDirection: 'column',
            justifyContent: 'space-between',
          }}
        >
          <Grid container spacing={2}>
            <TextFieldComponent
              defaultValue={''}
              required={true}
              label={'Event Title'}
              name={'eventName'}
              type={'text'}
            />
          </Grid>
          {!clicked && (
            <Button
              startIcon={<GroupAddIcon />}
              variant="contained"
              sx={{
                mt: 3,
                mb: 2,
                marginLeft: 'auto',
                bgcolor: 'secondary.main',
              }}
              onClick={() => setClicked(true)}
            >
              Add Event members
            </Button>
          )}
          {clicked && (
            <EventMembersListComponent
              onCheckedItemsChange={handleCheckedItemsChange}
            />
          )}
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Create Event
          </Button>
        </Box>
        <Box
          component="div"
          style={{ position: 'sticky', bottom: 0, width: '100%' }}
        ></Box>
      </Box>
      <IconButton
        aria-label="back"
        sx={{ bgcolor: 'secondary.main' }}
        onClick={() => navigate('/events')}
      >
        <ArrowBackIcon sx={{ color: 'primary.main' }} />
      </IconButton>
      <NavBar />
    </Container>
  );
};

export default CreateEvent;
