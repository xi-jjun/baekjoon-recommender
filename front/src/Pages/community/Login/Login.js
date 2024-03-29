import Button from "../../../Components/Button";
import * as Community from '../Community';
import * as Styled from './Styled';
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();

    const [id, setId] = useState("")
    const [pw, setPw] = useState("")

    const handleInputId = (e) => {
        setId(e.target.value)
    }
    const handleInputPw = (e) => {
        setPw(e.target.value)
    }

    const tryLogin = () => {

        const data = {
            username: id,
            password: pw,
        };

        // const adminInfo = require("../../admin/adminInfo.json");
        console.log("TEST");

        axios.post(process.env.REACT_APP_BASE_URL + "/login", data)
            .then((res) => {
                localStorage.setItem('Authorization', res.headers.authorization);
                console.log("login success");
                // console.log("res data: ", res.data);
                navigate("/", { replace: true });
                return;
            }).catch((e) => {
                console.log("err: ", e);
                alert("login failed");
            })

        // if (id == adminInfo.admin && pw == adminInfo.password) {
        //     // axios.post("http://localhost:8080/login", data)
        //     axios.post(process.env.REACT_APP_BASE_URL + "/login", data)
        //         .then((res) => {
        //             localStorage.setItem('Authorization', res.headers.authorization);
        //             console.log("admin login success");
        //             console.log("res data: ", res.data);
        //             navigate("/admin", { replace: true });
        //             return;
        //         }).catch((e) => {
        //             console.log("err: ", e);
        //             alert("login failed");
        //         })
        // }
        // else {
        //     // axios.post("http://localhost:8080/login", data)
        //     axios.post(process.env.REACT_APP_BASE_URL + "/login", data)
        //         .then((res) => {
        //             localStorage.setItem('Authorization', res.headers.authorization);
        //             console.log("login success");
        //             console.log("res data: ", res.data);
        //             navigate("/", { replace: true });
        //             return;
        //         }).catch((e) => {
        //             console.log("err: ", e);
        //             alert("login failed");
        //         })
        // }
    }

    return (
        <Styled.LoginForm>
            <Styled.LoginTypo>Login</Styled.LoginTypo>
            <Community.InfoContainer>
                <Community.Input
                    placeholder='아이디'
                    id='login-id'
                    onChange={handleInputId} />
                <Community.Input
                    placeholder='PW'
                    id='login-pw'
                    type="password"
                    onChange={handleInputPw} />
            </Community.InfoContainer>
            <Button onClick={tryLogin} typo="login" ID="login" />
            <Styled.LoginFormFooter>
                <Styled.LoginCheckboxWrapper>
                    <input type="checkbox"
                        style={{ width: "12px", height: "12px", borderColor: "#e5e5e5" }} />
                    <Styled.LoginFormFooterTypo>로그인 상태 유지</Styled.LoginFormFooterTypo>
                </Styled.LoginCheckboxWrapper>
                <Styled.SignUpLink to="../user/register">회원 가입</Styled.SignUpLink>
            </Styled.LoginFormFooter>
        </Styled.LoginForm >
    )
}

export default Login;