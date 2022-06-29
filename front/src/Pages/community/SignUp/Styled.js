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

export const InfoContainer = styled.div`
width: 100%;
display: flex;
flex-direction: column;
box-sizing: border-box;
margin: 10px 0;
`

export const InfoContainerLabel = styled.div`
width: 100%;
border-bottom: solid 2px #333;
box-sizing: border-box;
padding: 10px 0;
margin-bottom: 20px;
color: #333;
font-size: 20px;
font-weight: 600;
`

export const Info = styled.div`
width: 100%;
display: flex;
align-items: center;
box-sizing: border-box;
margin: 5px 0;
`

export const InfoLabel = styled.span`
min-width: 50px;
margin: 0 10px;
font-size: 20px;
`

export const ShortInput = styled.input`
width: 395px;
height: 40px;
box-sizing: border-box;
border: solid 1px #bbb;
padding: 0 24px;
margin: 5px 0;
`

export const BaekjoonLink = styled(Link)`
margin-left: 10px;
color: #333;
font-size: 13px;
text-decoration: none;
`
