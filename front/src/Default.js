import styled from 'styled-components';
import { Link } from "react-router-dom";

export const HeaderContainer = styled.div`
width: 100%;
height: 50px;
display: flex;
justify-content: space-between;
align-items: center;
border-bottom: solid 1px #e5e5e5;
box-sizing: border-box;
box-shadow: 0 0.25rem 0.75rem rgb(0 0 0 / 5%);
padding: 0 16px;
margin-bottom: 20px;
`

export const HeaderTypoContainer = styled.div`
display: flex;
justify-content: center;
align-items: center
`

export const HeaderTypo = styled.span`
color: #666;
font-size: 16px;
box-sizing: border-box;
margin: 10px;
`

export const HeaderLogo = styled.img`
width:100px;
height:32px;
background:#999;
`

export const SelectBoxArrow = styled.img`
width: 16px;
height: 16px;
`

export const SelectBoxContainer = styled.div`
height: 38px;
display: flex;
box-sizing: border-box;
margin: 10px 0;
position: relative;
`

export const SelectBoxLabel = styled.span`
width: 116px;
height: 38px;
display: flex;
align-items: center;
color: #333;
`

export const StyledLink = styled(Link)`
text-decoration: none;
`


export const DaysFilterContainer = styled.div`
width: 210px;
display: flex;
align-items: center;
border: solid 1px #0083e8;
border-right: none;
`

export const DaysFilter = styled.div`
width: 30px;
height: 30px;
display: flex;
justify-content: center;
align-items: center;
box-sizing: border-box;
border-right: solid 1px #0083e8;
font-size: 12px;
font-weight: 600;
cursor: pointer;
`

export const FilterContainer = styled.div`
height: 32px;
display: flex;
align-items: center;
margin: 5px 0;
`