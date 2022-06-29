import styled from "styled-components";
import { Link } from "react-router-dom";

export const Container = styled.div`
width: 588px;
display: flex;
flex-direction: column;
align-items: center;
box-sizing: border-box;
margin: auto;
padding: 60px 30px 30px 30px;
`

export const SignUpTypo = styled.div`
width: 100%;
color: #555;
font-size: 40px;
margin: 20px 0 60px;
`

export const BaekjoonLink = styled(Link)`
margin-left: 10px;
color: #333;
font-size: 13px;
text-decoration: none;
`
