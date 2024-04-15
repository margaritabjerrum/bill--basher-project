import React from 'react';
import { Box, Button } from '@mui/material';

const NavBar = () => {
  return (
    <Box display="flex" justifyContent="flex-end" sx={{ width: '100%' }}>
      <Button
        variant="contained"
        sx={{ mt: 3, width: '50%', borderRadius: 0, height: 40 }}
      >
        Events
      </Button>
      <Button
        variant="contained"
        sx={{ mt: 3, width: '50%', borderRadius: 0 }}
      >
        Account
      </Button>
    </Box>
  )
}

export default NavBar;