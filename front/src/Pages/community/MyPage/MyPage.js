import Header from "../../../Components/Header";
import * as Default from "../../../Default";
import * as Community from "../Community";
import * as Styled from "./Styled";
import Toggle from "../../../Components/Toggle";
import Button from "../../../Components/Button";
import { useEffect, useState } from "react";
import axios from "axios";

const MyPage = () => {

    const levels = [];
    const tags = [];

    const [data, setData] = useState(null);
    const [baekjoonID, setBaekjoonId] = useState("")
    const [newPw, setNewPw] = useState("")

    const handleInputNewPw = (e) => {
        setNewPw(e.target.value)
    }
    const handleInputId = (e) => {
        setBaekjoonId(e.target.value)
    }

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/user", {
            headers: {
                'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8;',
                'Authorization': localStorage.getItem("Authorization")
            }
        }).then(res => {
            const newData = res.data.data;
            setBaekjoonId(newData.baekJoonId)
            setData(newData);
            console.log(newData);
            const levels = newData.levels.split(",");
            const dailyTags = newData.dailyTags.split(",");
            for (let l of levels) {
                document.querySelector(`#my-page-${l}`).checked = true;
            }
            for (let t of dailyTags) {
                document.querySelector(`#my-page-daily-element-${t}`).checked = true;
            }
        }).catch(e => {
            console.log("error: ", e);
        })
    }, [])

    const tryConfirm = (e) => {

        e.preventDefault();
        for (let i = 0; i < 31; i++) {
            if (document.querySelector(`#my-page-${i}`).checked) {
                levels.push(i);
            }
        }

        for (let e of document.querySelectorAll(".my-page-daily-element")) {
            if (e.checked) {
                tags.push(e.getAttribute("value"))
            }
        }

        const days = ["mon", "tue", "wed", "thu", "fri", "sat", "sun"];
        const daysSelected = {
            mon: [],
            tue: [],
            wed: [],
            thu: [],
            fri: [],
            sat: [],
            sun: []
        };

        for (let day of days) {
            const docu = document.querySelector(`#my-page-schedule-${day}`)
            if (docu && docu.checked) {
                const elements = document.querySelectorAll(`#my-page-schedule-element-${day}`)
                for (let e of elements) {
                    if (e.checked) {
                        daysSelected[day].push(e.getAttribute("value"))
                    }
                }
            }
        }

        const signUpRequestDTO = {
            username: data.username,
            baekJoonId: baekjoonID,
            password: newPw,
            option: document.querySelector("#my-page-toggle").getAttribute("option"),
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

        console.log("sign up request: ", signUpRequestDTO);
        axios.defaults.headers.common['Authorization'] = localStorage.getItem("Authorization")
        axios.patch("http://localhost:8080/api/v1/user", signUpRequestDTO)
            .then(res => {
                console.log("res: ", res);
            }).catch(e => {
                console.log("err: ", e);
            })
    }

    return (
        <div>
            <Header />
            <Styled.Container>
                <Community.InfoContainer>
                    <Community.InfoContainerLabel>Membership Info</Community.InfoContainerLabel>
                    <Community.InfoSubContainer>
                        <Community.InfoLabel>PW</Community.InfoLabel>
                        <Community.Input placeholder="새로운 pw" onChange={handleInputNewPw} />
                    </Community.InfoSubContainer>
                    <Community.InfoSubContainer>
                        <Community.InfoLabel>Baekjoon ID</Community.InfoLabel>
                        <Community.ShortInput placeholder="새로운 Baekjoon ID" value={baekjoonID} onChange={handleInputId} />
                    </Community.InfoSubContainer>
                </Community.InfoContainer>
                <Community.InfoContainer>
                    <Community.InfoContainerLabel>Filter</Community.InfoContainerLabel>
                    <Community.DifficultyFilter page="my-page" />
                    <Toggle id="my-page-toggle" typo1="Daily" typo2="Schedule" element1={<Community.DailyFilter id="my-page-daily" />} element2={<Community.ScheduleFilter id="my-page-schedule" />} />
                    <div style={{ display: "flex", justifyContent: "center" }}>
                        <Default.StyledLink to="/" onClick={tryConfirm}><Button typo="Confirm" /></Default.StyledLink>
                    </div>
                </Community.InfoContainer>
            </Styled.Container>
        </div>
    )
}

export default MyPage;