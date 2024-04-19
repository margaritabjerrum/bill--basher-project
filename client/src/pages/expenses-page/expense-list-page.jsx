import React from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Box,
  Button,
  Container,
  CssBaseline,
  Grid,
  Typography,
} from '@mui/material';
import ExpenseListComponent from './expense-list-component';
import NavBar from '../../components/layout/navbar/navbar';
import LogoComponent from '../../components/logo/logo-component';
import { useParams } from 'react-router-dom';
import ApiService from '../../services/api-service';

const ExpenseListPage = () => {
  const navigate = useNavigate();
  const { eventId } = useParams();
  const [eventName, setEventName] = React.useState('');

  React.useEffect(() => {
    (async () => {
      const eventData = await ApiService.getEventById(eventId);
      setEventName(eventData.data.eventName);
    })();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const onClickViewMembers = () => {
    console.log('clicked: onClickViewMembers');
  };

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
          }}
        >
          <LogoComponent />
          <Typography
            variant="h4"
            component="h1"
            sx={{ color: 'primary.dark' }}
          >
            {eventName}
          </Typography>
          <Box display="flex" justifyContent="flex-end" sx={{ width: '100%' }}>
            <Button
              variant="contained"
              sx={{ mt: 3, mb: 2, width: '50%' }}
              onClick={onClickViewMembers}
            >
              View Members
            </Button>
            <Button
              variant="contained"
              sx={{ mt: 3, mb: 2, width: '50%', ml: 2 }}
              onClick={() => navigate(`/createexpense/${eventName}/${eventId}`)}
            >
              Add new Expense
            </Button>
          </Box>

          <Box sx={{ width: '100%', height: '100%' }}>
            <ExpenseListComponent eventId={eventId} />
          </Box>
        </Box>
        <NavBar />
      </Container>
    </>
  );
};

export default ExpenseListPage;
