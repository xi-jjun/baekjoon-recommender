import React from 'react';
import ReactDOM from 'react-dom/client';
import Root from "./client/Root";
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Root />
  </React.StrictMode>
);
reportWebVitals();
