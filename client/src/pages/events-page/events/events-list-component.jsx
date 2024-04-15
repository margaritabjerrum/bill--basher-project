import React from 'react';
import { List, ListItem, ListItemText, ListItemButton } from '@mui/material';

const eventList = ['event1', 'event2', 'event3', 'event4', 'event5', 'event6'];

function EventsListComponent() {
  
  return (
    <List
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
      }}
    >
      {eventList.map((event, index) => (
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
          <ListItemButton component="a" href={`#${event}`}>
            <ListItemText primary={event} />
          </ListItemButton>
        </ListItem>
      ))}
    </List>
  );
}

export default EventsListComponent;
