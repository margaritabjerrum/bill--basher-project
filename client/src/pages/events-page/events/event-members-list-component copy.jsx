import React, { useState } from 'react';
import { List, ListItem, ListItemText, ListItemButton, Checkbox } from '@mui/material';

const PeopleList = ['Person1', 'Person2', 'Person3', 'Person4', 'Person5', 'Person6'];

// eslint-disable-next-line react/prop-types
function EventMembersListComponent({ onCheckedItemsChange }) {
  const [checkedItems, setCheckedItems] = useState({});

  const handleToggle = (person) => {
    setCheckedItems((prev) => ({
      ...prev,
      [person]: !prev[person],
    }));
    onCheckedItemsChange({ ...checkedItems, [person]: !checkedItems[person] });
  };

  return (
    <List
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
      }}
    >
      {PeopleList.map((person) => (
        <ListItem
          key={person}
          disablePadding
          sx={{
            my: 0.5,
            bgcolor: 'secondary.main',
            width: '100%',
            borderRadius: 2,
          }}
        >
          <ListItemButton component="div">
            <Checkbox
              checked={checkedItems[person] || false}
              onChange={() => handleToggle(person)}
            />
            <ListItemText primary={person} />
          </ListItemButton>
        </ListItem>
      ))}
    </List>
  );
}

export default EventMembersListComponent;