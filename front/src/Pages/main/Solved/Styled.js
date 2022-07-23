import styled from "styled-components";

export const Container = styled.div`
width: 1200px;
display: flex;
box-sizing: border-box;
margin: auto;
padding: 30px;
`

export const QuestionContainer = styled.div`
width: 800px;
max-height: 800px;
display: flex;
flex-direction: column;
box-sizing: border-box;
margin: 0 15px;
`

export const QuestionLabelContainer = styled.div`
width: 100%;
height: 56px;
display: flex;
align-items: center;
border-top: solid 2px #e5e5e5;
border-bottom: solid 1px #e5e5e5;
box-sizing: border-box;
padding: 0 10px;
color: #333;
font-size: 20px;
font-weight: 600;
`

export const QuestionLabel = styled.div`
width: 150px;
display: flex;
align-items: center;
`

export const Question = styled.div`
width: 100%;
height: 44px;
display: flex;
align-items: center;
border-bottom: solid 1px #e5e5e5;
cursor: pointer;
`

export const QuestionElement = styled.div`
width: 150px;
display: flex;
align-items: center;
box-sizing: border-box;
padding: 0 10px;
color: #555;
font-size: 16px;
`

export const QuestionUnSolvedContainer = styled.div`
width: 450px;
display: flex;
flex-direction: column;
box-sizing: border-box;
margin: 0 15px;
`

export const PaginationContainer = styled.div`
display: flex;
justify-content: center;
align-items: center;
border: solid 1px #0083e8;
border-right: none;
box-sizing: border-box;
margin: 30px auto;
`