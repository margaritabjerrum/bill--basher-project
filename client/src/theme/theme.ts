import { createTheme } from '@mui/material';

const theme = createTheme({
  palette: {
    primary: {
      // main: '#19e2d1',
      main: '#f78a93',
      // light: '#d0d175',
      // dark: '#646e32',
    },
    secondary: {
      // main: '#ff4657',
      main: '#9ce0db',
      // light: '#d0d175',
      // dark: '#646e32',
    },
    background: {
      default: '#76a4b1',
    },
  },
  // typography: {
  //   allVariants: {
  //     color: '#ffffff',
  //   },
  // },
  zIndex: {
    appBar: 1250,
  },
});

export default theme;
