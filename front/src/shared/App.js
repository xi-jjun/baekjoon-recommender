import { React } from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import Login from '../Pages/community/Login/Login';
import SignUp from '../Pages/community/SignUp/SignUp';
import Recommend from '../Pages/main/Recommend/Recommend';
import Rival from "../Pages/main/Rival/Rival";
import Solved from "../Pages/main/Solved/Solved";
import MyPage from '../Pages/community/MyPage/MyPage';

function App() {

    return (
        <Routes>
            {localStorage.getItem("Authorization") &&
                <Route path="/" element={<Recommend />} />
            }
            <Route path="/user/login" element={<Login />} />
            <Route path="/user/register" element={<SignUp />} />
            {localStorage.getItem("Authorization") &&
                <Route path="/user/my-page" element={<MyPage />} />
            }{localStorage.getItem("Authorization") &&
                <Route path="/solved" element={<Solved />} />
            }{localStorage.getItem("Authorization") &&
                <Route path="/rival" element={<Rival />} />
            }
        </Routes>
    );
}

export default App;