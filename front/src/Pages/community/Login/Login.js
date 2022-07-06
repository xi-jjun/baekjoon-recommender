import Button from "../../../Components/Button";
import * as Default from "../../../Default";
import * as Community from '../Community';
import * as Styled from './Styled';
import { useEffect, useState } from "react";
import axios from "axios";

const LoginInfo = {
    typo: "login",
    id: "login"
}

const Login = () => {
    let loginSuccess = false;
    const [id, setId] = useState("")
    const [pw, setPw] = useState("")

    const handleInputId = (e) => {
        setId(e.target.value)
    }
    const handleInputPw = (e) => {
        setPw(e.target.value)
    }

    // useEffect(() => {
    //     const getData = async () => {
    //         const res = await axios.post("https://solved.ac/api/v3/problem/show?problemId=1001", {
    //             username,
    //             password
    //         })
    //         //login 여부를 나타내는 변수 값 수정
    //         console.log(res.data.titleKo)
    //     }
    //     getData()
    // }, [])

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
            <Default.StyledLink id="login-button" to="/recommend"><Button typo={LoginInfo.typo} ID={LoginInfo.id} /></Default.StyledLink>
            <Styled.LoginFormFooter>
                <Styled.LoginCheckboxWrapper>
                    <input type="checkbox"
                        style={{ width: "12px", height: "12px", borderColor: "#e5e5e5" }} />
                    <Styled.LoginFormFooterTypo>로그인 상태 유지</Styled.LoginFormFooterTypo>
                </Styled.LoginCheckboxWrapper>
                <Styled.SignUpLink to="/signUp">회원 가입</Styled.SignUpLink>
            </Styled.LoginFormFooter>
        </Styled.LoginForm>
    )
}

export default Login;