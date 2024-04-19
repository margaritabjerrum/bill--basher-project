import React from 'react';
import {
  List,
  ListItem,
  ListItemText,
  ListItemButton,
  Typography,
} from '@mui/material';
import ApiService from '../../services/api-service';

// eslint-disable-next-line react/prop-types
const ExpenseListComponent = ({ eventId }) => {
  const [expenseList, setEventsList] = React.useState([]);

  React.useEffect(() => {
    (async () => {
      const expenseList = await ApiService.getExpensesPerEvent(eventId);
      setEventsList(expenseList.data);
    })();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      {expenseList.length === 0 && (
        <Typography variant="h5" component="h2">
          Please create new expense
        </Typography>
      )}
      {expenseList && (
        <List
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          {expenseList.map((expense, index) => (
            <ListItem
              key={index}
              disablePadding
              sx={{
                my: 0.5,
                bgcolor: 'secondary.main',
                width: '100%',
                borderRadius: 2,
              }}
            >
              <ListItemButton
                component="a"
                href={`#${expense.expenseReason}`}
                sx={{ my: 0, py: 0 }}
              >
                <ListItemText
                  primary={
                    <Typography variant="body2">
                      {expense.expenseReason +
                        ' Paid by ' +
                        expense.userId.username +
                        ' Total: ' +
                        expense.amountSpent +
                        '€'}
                    </Typography>
                  }
                />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
      )}
    </>
  );
};

export default ExpenseListComponent;
