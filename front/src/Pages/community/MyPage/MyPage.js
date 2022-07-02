import Header from "../../../Components/Header";
import * as Default from "../../../Default";
import * as Community from "../Community";
import * as Styled from "./Styled";
import CommunityButton from "../../../Components/CommunityButton";
import Toggle from "../../../Components/Toggle";
import Button from "../../../Components/Button";
import { useEffect, useState } from "react";
import axios from "axios";

const MyPage = () => {
    const [existingPw, setExistingPw] = useState("")
    const [newPw, setNewPw] = useState("")

    const handleInputExistingPw = (e) => {
        setExistingPw(e.target.value)
    }
    const handleInputNewPw = (e) => {
        setNewPw(e.target.value)
    }

    // useEffect(() => {
    //     const getData = async () => {
    //         const res = await axios.get("/user/my-page")
    //         console.log(res.data)
    //     }
    //     getData()
    // })

    return (
        <div>
            <Header />
            <Styled.Container>
                <Community.InfoContainer>
                    <Community.InfoContainerLabel>Membership Info</Community.InfoContainerLabel>
                    <Community.InfoSubContainer>
                        <Community.InfoLabel>ID</Community.InfoLabel>
                        <Styled.Info>baekjoonRecommender</Styled.Info>
                    </Community.InfoSubContainer>
                    <Community.InfoSubContainer>
                        <Community.InfoLabel>PW</Community.InfoLabel>
                        <Community.Input placeholder="기존 pw" onChange={handleInputExistingPw} />
                    </Community.InfoSubContainer>
                    <Community.InfoSubContainer>
                        <Community.InfoLabel></Community.InfoLabel>
                        <Community.ShortInput placeholder="새로운 pw" onChange={handleInputNewPw} />
                        <CommunityButton typo="수정" />
                    </Community.InfoSubContainer>
                </Community.InfoContainer>
                <Community.InfoContainer>
                    <Community.InfoContainerLabel>Filter</Community.InfoContainerLabel>
                    <Toggle typo1="Daily" typo2="Schedule" element1={<Community.DailyFilter />} element2={<Community.ScheduleFilter />} />
                    <div style={{ display: "flex", justifyContent: "center" }}>
                        <Default.StyledLink to="/recommend"><Button typo="Confirm" /></Default.StyledLink>
                    </div>
                </Community.InfoContainer>
            </Styled.Container>
        </div>
    )
}

export default MyPage;