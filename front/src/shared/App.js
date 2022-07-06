import { React } from 'react';
import { Route, Routes } from 'react-router-dom';
import Login from '../Pages/community/Login/Login';
import SignUp from '../Pages/community/SignUp/SignUp';
import Recommend from '../Pages/main/Recommend/Recommend';
import Rival from "../Pages/main/Rival/Rival";
import Solved from "../Pages/main/Solved/Solved";
import MyPage from '../Pages/community/MyPage/MyPage';

function App() {
    return (
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/signUp" element={<SignUp />} />
            <Route path="/recommend" element={<Recommend />} />
            <Route path="/solved" element={<Solved />} />
            <Route path="/rival" element={<Rival />} />
            <Route path="/myPage" element={<MyPage />} />
        </Routes>
    );
}

export default App;