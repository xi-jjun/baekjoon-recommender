import Button from "../../../Components/Button";
import * as Default from "../../../Default";
import * as Community from '../Community';
import * as Styled from './Styled';
import { useEffect, useState, } from "react";
import axios from "axios";

const LoginInfo = {
    typo: "login",
    id: "login"
}

const Login = () => {
    const [id, setId] = useState("")
    const [pw, setPw] = useState("")

    const handleInputId = (e) => {
        setId(e.target.value)
    }
    const handleInputPw = (e) => {
        setPw(e.target.value)
    }

    const [token, setToken] = useState("");

    const tryLogin = (e) => {

        const data = {
            username: id,
            password: pw,
        };

        axios.post("http://localhost:8080/login", data)
            .then((res) => {
                console.log("Data: ", data);
                console.log("res.data: ", res.data);
                localStorage.setItem('Authorization', res.headers.authorization);
                setToken(localStorage.getItem("Authorization"));

            }).catch((e) => {
                console.log("err: ", e);
                alert("login failed");
                console.log(localStorage.getItem("Authorization"));
            })
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
            <Default.StyledLink id="login-button" replace to={token ? "/" : "/user/login"} onClick={tryLogin}><Button typo={LoginInfo.typo} ID={LoginInfo.id} /></Default.StyledLink>
            <Styled.LoginFormFooter>
                <Styled.LoginCheckboxWrapper>
                    <input type="checkbox"
                        style={{ width: "12px", height: "12px", borderColor: "#e5e5e5" }} />
                    <Styled.LoginFormFooterTypo>로그인 상태 유지</Styled.LoginFormFooterTypo>
                </Styled.LoginCheckboxWrapper>
                <Styled.SignUpLink to="../user/register">회원 가입</Styled.SignUpLink>
            </Styled.LoginFormFooter>
        </Styled.LoginForm>
    )
}

export default Login;