import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Admin = () => {
    const navigation = useNavigate();

    const [userId, setUserId] = useState(-1);
    const handleUserId = (e) => {
        setUserId(e.target.value);
    }

    const resetAllUsersReloadCount = () => {
        axios.defaults.headers.common['Authorization'] = localStorage.getItem("Authorization")
        axios.patch("http://localhost:8080/api/v1/system/reload-count")
            .then(res => {
                console.log("res: ", res);
            }).catch(e => {
                console.log("err: ", e);
            })
    }

    const reloadCountReset = () => {
        console.log("user id: ", userId);
        axios.defaults.headers.common['Authorization'] = localStorage.getItem("Authorization")
        axios.patch(`http://localhost:8080/api/v1/system/${userId}/reload-count`)
            .then(res => {
                console.log("res: ", res);
            }).catch(e => {
                console.log("err: ", e);
            })
    }

    const updateProblemList = () => {
        axios.defaults.headers.common['Authorization'] = localStorage.getItem("Authorization")
        axios.patch('http://localhost:8080/api/v1/system/problem-list')
            .then(res => {
                console.log("res: ", res);
            }).catch(e => {
                console.log("err: ", e);
            })
    }

    return (
        <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
            <button onClick={resetAllUsersReloadCount}>resetAllUsersReloadCount</button>
            <input id="reload-count-user-id" type="number" min="1" onChange={handleUserId} />
            <button onClick={reloadCountReset}>reloadCountReset</button>
            <button onClick={updateProblemList}>updateProblemList</button>
            <button onClick={() => {
                localStorage.removeItem("Authorization");
                navigation("/user/login", { replace: true });
            }}>toLogin</button>
        </div>
    )
}

export default Admin;