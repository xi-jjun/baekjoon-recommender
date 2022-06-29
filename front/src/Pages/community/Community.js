import styled from "styled-components";
import * as Default from "../../Default";
import SelectBox from "../../Components/SelectBox";
import DaysFilter from "../../Components/DaysFilter";


const questionTypeOptions = ["dp", "brute force", "sort"]
const difficultyGradeOptions = ["bronze", "silver", "gold", "platinum", "diamond", "ruby"]
const difficultyLevelOptions = [1, 2, 3, 4, 5]

export const DailyFilter = () => {
    return (
        <div>
            <Default.SelectBoxContainer>
                <Default.SelectBoxLabel>문제 유형</Default.SelectBoxLabel>
                <SelectBox selectTypo="문제 유형" options={questionTypeOptions} ></SelectBox>
            </Default.SelectBoxContainer>
            <Default.SelectBoxContainer>
                <Default.SelectBoxLabel>난이도</Default.SelectBoxLabel>
                <SelectBox selectTypo="등급" options={difficultyGradeOptions} ></SelectBox>
                <SelectBox selectTypo="레벨" options={difficultyLevelOptions} ></SelectBox>
            </Default.SelectBoxContainer>
        </div >
    )
}

export const ScheduleFilter = () => {
    return (
        <DaysFilter />
    )
}


export const InfoContainer = styled.div`
width: 100%;
display: flex;
flex-direction: column;
justify-content: space-between;
box-sizing: border-box;
padding: 20px 0;
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

export const InfoLabel = styled.span`
min-width: 50px;
margin: 0 10px;
font-size: 20px;
`

export const InfoSubContainer = styled.div`
width: 100%;
display: flex;
align-items: center;
`

export const Input = styled.input`
width: 100%;
max-width: 530px;
height: 40px;
box-sizing: border-box;
border: solid 1px #bbb;
padding: 0 24px;
margin: 5px 0;
`
export const ShortInput = styled.input`
width: 395px;
height: 40px;
box-sizing: border-box;
border: solid 1px #bbb;
padding: 0 24px;
margin: 5px 0;
`