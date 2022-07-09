import * as Default from '../../../Default';
import * as Community from '../Community';
import * as Styled from './Styled';
import Button from "../../../Components/Button";
import CommunityButton from '../../../Components/CommunityButton';
import Toggle from '../../../Components/Toggle';
import { useEffect, useState } from 'react';
import axios from 'axios';

function SignUp() {
    const [id, setId] = useState("")
    const [pw, setPw] = useState("")
    const [baekjoonId, setBaekjoonId] = useState("")

    const handleInputId = (e) => {
        setId(e.target.value)
    }
    const handleInputPw = (e) => {
        setPw(e.target.value)
    }
    const handleInputBaekjoonId = (e) => {
        setBaekjoonId(e.target.value)
    }

    const trySignUp = (e) => {
        const userRequestDTO = {
            username: id,
            baekJoonId: baekjoonId,
            password: pw
        };

        const levels = [];
        for (let i = 0; i < 31; i++) {
            if (document.querySelector(`#sign-up-${i}`).checked) {
                levels.push(i);
            }
        }

        const tags = []
        for (let e of document.querySelectorAll("#sign-up-daily-element")) {
            if (e.checked) {
                tags.push(e.getAttribute("value"))
            }
        }

        const days = ["mon", "tue", "wed", "thu", "fri", "sat", "sun"]
        const daysSelected = {
            mon: [],
            tue: [],
            wed: [],
            thu: [],
            fri: [],
            sat: [],
            sun: []
        }
        for (let day of days) {
            const docu = document.querySelector(`#sign-up-schedule-${day}`)
            if (docu && docu.checked) {
                const elements = document.querySelectorAll(`#sign-up-schedule-element-${day}`)
                for (let e of elements) {
                    if (e.checked) {
                        daysSelected[day].push(e.getAttribute("value"))
                    }
                }
            }
        }


        const settingRequestDTO = {
            option: document.querySelector("#sign-up-toggle").getAttribute("option"),
            levels: levels.join(),
            tags: tags.join(),
            sun: daysSelected["sun"].join(),
            mon: daysSelected["mon"].join(),
            tue: daysSelected["tue"].join(),
            wed: daysSelected["wed"].join(),
            thu: daysSelected["thu"].join(),
            fri: daysSelected["fri"].join(),
            sat: daysSelected["sat"].join()
        };

        const signUpRequestDTO = {
            userRequestDTO: userRequestDTO,
            settingRequestDTO: settingRequestDTO
        };

        axios.post('http://localhost:8080/api/v1/user', signUpRequestDTO)
            .then(res => {
                console.log("data: ", res.data);
                console.log("sign up success");
            })
            .catch(e => console.log("err: ", e));
    }

    return (
        <Styled.Container>
            <Styled.SignUpTypo>Sign Up</Styled.SignUpTypo>
            <Community.InfoContainer>
                <Community.InfoContainerLabel>기본 정보</Community.InfoContainerLabel>
                <Community.InfoSubContainer>
                    <Community.InfoLabel>ID</Community.InfoLabel>
                    <Community.Input placeholder='id' id="new_id" type="text" onChange={handleInputId} />
                    <CommunityButton typo="중복확인" />
                </Community.InfoSubContainer>
                <Community.InfoSubContainer>
                    <Community.InfoLabel>PW</Community.InfoLabel>
                    <Community.Input placeholder='pw' id="new_pw" type="password" onChange={handleInputPw} />
                </Community.InfoSubContainer>
            </Community.InfoContainer>
            <Community.InfoContainer>
                <Community.InfoContainerLabel>BaekJoon 정보</Community.InfoContainerLabel>
                <Community.InfoSubContainer>
                    <Community.InfoLabel>BaekJoon ID</Community.InfoLabel>
                    <Community.ShortInput placeholder='백준 id' id="baekjoon_id" type="text" onChange={handleInputBaekjoonId} />
                </Community.InfoSubContainer>
                <Styled.BaekjoonLink replace to={{ pathname: "https://www.acmicpc.net/" }} target="_blank">baekjoon online judge 바로 가기</Styled.BaekjoonLink>
            </Community.InfoContainer>
            <Community.InfoContainer>
                <Community.InfoContainerLabel>Filter</Community.InfoContainerLabel>
                <Community.DifficultyFilter />
                <Toggle id="sign-up-toggle" typo1="Daily" typo2="Schedule" element1={<Community.DailyFilter id="sign-up-daily" />} element2={<Community.ScheduleFilter id="sign-up-schedule" />} />
            </Community.InfoContainer>
            <Default.StyledLink to="/" onClick={trySignUp}><Button typo="Sign Up" /></Default.StyledLink>
        </Styled.Container>
    )
}

export default SignUp;