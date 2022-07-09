import { React } from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import Login from '../Pages/community/Login/Login';
import SignUp from '../Pages/community/SignUp/SignUp';
import Recommend from '../Pages/main/Recommend/Recommend';
import Rival from "../Pages/main/Rival/Rival";
import Solved from "../Pages/main/Solved/Solved";
import MyPage from '../Pages/community/MyPage/MyPage';

const getToken = () => {
    const tokenString = localStorage.getItem("Authorization")
    const userToken = JSON.parse(tokenString)
    return userToken?.token
}

function App() {
    const token = getToken()
    console.log("token: ", token)

    return (
        <Routes>
            <Route path="/" element={token ? <Recommend /> : <Navigate to="/user/login" />} />
            <Route path="/user/login" element={<Login />} />
            <Route path="/user/register" element={<SignUp />} />
            <Route path="/user/my-page" element={token ? <MyPage /> : <Navigate to="/user/login" />} />
            <Route path="/solved" element={token ? <Solved /> : <Navigate to="/user/login" />} />
            <Route path="/rival" element={token ? <Rival /> : <Navigate to="/user/login" />} />
        </Routes>
    );
}

export default App;