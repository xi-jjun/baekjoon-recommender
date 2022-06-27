import React from 'react';
import ReactDOM from 'react-dom/client';
import Root from "./client/Root";
import Login from './Pages/community/Login/Login';
import SignUp from './Pages/community/SignUp/SignUp';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Root />
  </React.StrictMode>
);
reportWebVitals();
