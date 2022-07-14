import styled from "styled-components";
import { Link } from "react-router-dom";

export const Container = styled.div`
width: 100%;
display:flex;
flex-direction: column;
justify-content: center;
align-items: center;
`

export const QuestionForm = styled.div`
width: 688px;
display: flex;
flex-direction: column;
align-items: center;
border: solid 2px #d5d5d5;
border-radius: 15px;
box-sizing: border-box;
padding: 30px;
margin: 35px;
`

export const QuestionFormTypo = styled.div`
width: 100%;
display: flex;
justify-content: center;
align-items: center;
box-sizing: border-box;
margin: 20px 0;
color: #333;
font-size: 40px;
`

export const QuestionTable = styled.div`
width: 600px;
border: solid 1px #d5d5d5;
border-radius: 6px;
`

export const QuestionTableDivider = styled.div`
width: 580px;
height: 1px;
background: #d5d5d5;
box-sizing: border-box;
margin: 0 10px;
`

export const Question = styled.div`
display: flex;
justify-content: center;
align-items: center;
box-sizing: border-box;
padding: 10px 0;
`

export const QuestionElement = styled.div`
width: 200px;
height: 38px;
display: flex;
justify-content: center;
align-items: center;
`

export const QuestionShortCut = styled(Link)`
width: 100%;
color: #666;
font-size: 16px;
text-decoration: none;
box-sizing: border-box;
padding: 0 20px;
`

export const AdditionalQuestionForm = styled.div`
width: 688px;
height: 223px;
display: flex;
flex-direction: column;
box-sizing: border-box;
margin: 35px;
`

export const AdditionalQuestionButtonWrapper = styled.div`
width: 100%;
display: flex;
justify-content: flex-end;
margin-bottom: 30px;
`

export const AdditionalQuestionButtonContainer = styled.div`
width: 100px;
`

export const FilterCheckBoxContainer = styled.div`
width: 688px;
display: flex;
align-items: center;
`

export const FilterCheckBoxLabel = styled.span`
color: #333;
font-size: 13px;
`