import Button from "../../../Components/Button";
import * as Default from "../../../Default";
import * as Community from '../Community';
import * as Styled from './Styled';
import { useCallback, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
    const toMain = useCallback(() => navigate("/", { replace: true }), [navigate]);

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

        axios.post("http://localhost:8080/login", data)
            .then((res) => {
                localStorage.setItem('Authorization', res.headers.authorization);
                console.log("login success");
                console.log("res data: ", res.data);
                toMain();
            }).catch((e) => {
                console.log("err: ", e);
                alert("login failed");
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
            <Default.StyledLink id="login-button" to="/" onClick={tryLogin}><Button typo="login" ID="login" /></Default.StyledLink>
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