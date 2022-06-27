import styled from 'styled-components';
import { Link } from "react-router-dom";

export const LoginForm = styled.div`
width: 412px;
height: 350px;
border: solid 3px #e5e5e5;
border-radius: 12px;
box-sizing: border-box;
margin: 150px auto;
padding: 40px;
`

export const LoginTypo = styled.div`
width: 100%;
display: flex;
align-items: center;
box-sizing: border-box;
margin: 8px 0;
color: #555;
font-size: 20px;
`

export const LoginFormFooter = styled.div`
width: 100%;
display: flex;
justify-content: space-between;
align-items: center;
font-size: 12px;
box-sizing: border-box;
margin-top: 10px
`

export const LoginCheckboxWrapper = styled.span`
display: flex;
align-items: center;
`

export const LoginFormFooterTypo = styled.span`
color: #a6a6a6;
`

export const SignUpLink = styled(Link)`
color: "#666";
cursor: pointer;
text-decoration: none;
`