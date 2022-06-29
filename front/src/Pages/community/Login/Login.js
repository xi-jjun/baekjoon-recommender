import Button from "../../../Components/Button";
import $ from 'jquery';
import * as Default from "../../../Default";
import * as Community from '../Community';
import * as Styled from './Styled';
import { Link } from "react-router-dom";

$(function () {
    $("#login").on("click", function () {
        let loginInfo = {
            ID: $('#login-id').val(),
            PW: $('#login-pw').val()
        }
    })
})

const LoginInfo = {
    typo: "login",
    id: "login"
}

const Login = () => {
    return (
        <Styled.LoginForm>
            <Styled.LoginTypo>Login</Styled.LoginTypo>
            <Community.InfoContainer>
                <Community.Input
                    placeholder='아이디'
                    id='login-id' />
                <Community.Input
                    placeholder='PW'
                    id='login-pw' />
            </Community.InfoContainer>
            <Default.StyledLink to="/recommend"><Button typo={LoginInfo.typo} ID={LoginInfo.id} /></Default.StyledLink>
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