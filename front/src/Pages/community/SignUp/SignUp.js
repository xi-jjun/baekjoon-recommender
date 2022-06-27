import * as Default from '../../../Default';
import * as Community from '../Community';
import * as Styled from './Styled';
import Button from "../../../Components/Button";
import SignUpButton from '../../../Components/SignUpButton';
import Toggle from '../../../Components/Toggle';
import SelectBox from '../../../Components/SelectBox';
import DaysFilter from '../../../Components/DaysFilter';
import { useState } from 'react';

const DailyFilter = () => {
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

const ScheduleFilter = () => {
    return (
        <div>
            <Styled.DaysFilterContainer>
                <DaysFilter typo="월" />
                <DaysFilter typo="화" />
                <DaysFilter typo="수" />
                <DaysFilter typo="목" />
                <DaysFilter typo="금" />
                <DaysFilter typo="토" />
                <DaysFilter typo="일" />
            </Styled.DaysFilterContainer>
            <Default.SelectBoxContainer id="월" className='hidden'>
                <Default.SelectBoxLabel>문제 유형</Default.SelectBoxLabel>
            </Default.SelectBoxContainer>
            <Default.SelectBoxContainer id="화" className='hidden'>
                <Default.SelectBoxLabel>문제 유형</Default.SelectBoxLabel>
            </Default.SelectBoxContainer>
            <Default.SelectBoxContainer id="목" className='hidden'>
                <Default.SelectBoxLabel>문제 유형</Default.SelectBoxLabel>
            </Default.SelectBoxContainer>
            <Default.SelectBoxContainer id="금" className='hidden'>
                <Default.SelectBoxLabel>문제 유형</Default.SelectBoxLabel>
            </Default.SelectBoxContainer>
            <Default.SelectBoxContainer id="토" className='hidden'>
                <Default.SelectBoxLabel>문제 유형</Default.SelectBoxLabel>
            </Default.SelectBoxContainer>
            <Default.SelectBoxContainer id="일" className='hidden'>
                <Default.SelectBoxLabel>문제 유형</Default.SelectBoxLabel>
            </Default.SelectBoxContainer>
        </div>
    )
}

const questionTypeOptions = ["dp", "brute force", "sort"]
const difficultyGradeOptions = ["bronze", "silver", "gold", "platinum", "diamond", "ruby"]
const difficultyLevelOptions = [1, 2, 3, 4, 5]

function SignUp() {
    return (
        <Styled.Container>
            <Styled.SignUpTypo>Sign Up</Styled.SignUpTypo>
            <Community.InputContainer>
                <Styled.InfoContainerLabel>기본 정보</Styled.InfoContainerLabel>
                <Community.InputSubContainer>
                    <Styled.InfoLabel>ID</Styled.InfoLabel>
                    <Community.Input placeholder='id' id="new_id" type="text" />
                    <SignUpButton typo="중복확인" />
                </Community.InputSubContainer>
                <Community.InputSubContainer>
                    <Styled.InfoLabel>PW</Styled.InfoLabel>
                    <Community.Input placeholder='pw' id="new_pw" type="password" />
                </Community.InputSubContainer>
            </Community.InputContainer>
            <Community.InputContainer>
                <Styled.InfoContainerLabel>BaekJoon 정보</Styled.InfoContainerLabel>
                <Community.InputSubContainer>
                    <Styled.InfoLabel>BaekJoon ID</Styled.InfoLabel>
                    <Styled.ShortInput placeholder='백준 id' id="baekjoon_id" type="text" />
                </Community.InputSubContainer>
                <Styled.BaekjoonLink to={{ pathname: "https://www.acmicpc.net/" }} target="_blank">baekjoon online judge 바로 가기</Styled.BaekjoonLink>
            </Community.InputContainer>
            <Community.InputContainer>
                <Styled.InfoContainerLabel>Filter</Styled.InfoContainerLabel>
                <Toggle typo1="Daily" typo2="Schedule" element1={<DailyFilter />} element2={<ScheduleFilter />} />
            </Community.InputContainer>
        </Styled.Container>
    )
}

export default SignUp;