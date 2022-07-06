import styled from "styled-components";
import "../../default.css";
import * as Default from "../../Default";
import SelectBox from "../../Components/SelectBox";
import DaysFilter from "../../Components/DaysFilter";
import { useState } from "react";


const questionTypeOptions = ["유형1", "유형2"]
const difficultyGradeOptions = ["bronze", "silver", "gold", "platinum", "diamond", "ruby"]
const difficultyLevelOptions = [1, 2, 3, 4, 5]

export const DailyFilter = () => {

    const [onMouseOver, setMouseOver] = useState(false)
    const getMouseOver = () => {
        setMouseOver(true)
    }
    const getMouseOut = () => {
        setMouseOver(false)
    }

    const FilterElement = ({ typo }) => {

        const [filterClicked, onClickFilter] = useState(false)
        const clickFilter = () => {
            onClickFilter(prev => !prev)
        }

        return (
            <div style={{
                minWidth: "32px",
                height: "32px",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                background: filterClicked ? "#0083e8" : "#b8b8b8",
                border: filterClicked ? "solid 1px #0083e8" : null,
                borderRadius: "3px",
                color: "#fff",
                fontSize: "14px",
                boxSizing: "border-box",
                margin: "0 4px",
                padding: "0 4px",
                cursor: "pointer",
                transition: "all 0.1s ease-out"
            }}
                onClick={clickFilter}>
                <div>{typo}</div>
                <div style={{
                    fontSize: "12.8px",
                    margin: "0 0 0 8px"
                }}
                    className={filterClicked ? "" : "hidden"}>
                    x
                </div>
            </div>
        )
    }

    return (
        <div>
            <div style={{ display: "flex", margin: "5px 0" }}>
                <FilterElement typo="유형1" />
                <FilterElement typo="유형2" />
            </div >
            <div style={{ display: "flex", margin: "5px 0" }}>
                <FilterElement typo="Unranked" />
            </div>
            <div style={{ display: "flex", margin: "5px 0" }}>
                <FilterElement typo="Bronze" />
                <FilterElement typo="1" />
                <FilterElement typo="2" />
                <FilterElement typo="3" />
                <FilterElement typo="4" />
                <FilterElement typo="5" />
            </div >
            <div style={{ display: "flex", margin: "5px 0" }}>
                <FilterElement typo="Silver" />
                <FilterElement typo="1" />
                <FilterElement typo="2" />
                <FilterElement typo="3" />
                <FilterElement typo="4" />
                <FilterElement typo="5" />
            </div >
            <div style={{ display: "flex", margin: "5px 0" }}>
                <FilterElement typo="Gold" />
                <FilterElement typo="1" />
                <FilterElement typo="2" />
                <FilterElement typo="3" />
                <FilterElement typo="4" />
                <FilterElement typo="5" />
            </div >
            <div style={{ display: "flex", margin: "5px 0" }}>
                <FilterElement typo="Platinum" />
                <FilterElement typo="1" />
                <FilterElement typo="2" />
                <FilterElement typo="3" />
                <FilterElement typo="4" />
                <FilterElement typo="5" />
            </div >
            <div style={{ display: "flex", margin: "5px 0" }}>
                <FilterElement typo="Diamond" />
                <FilterElement typo="1" />
                <FilterElement typo="2" />
                <FilterElement typo="3" />
                <FilterElement typo="4" />
                <FilterElement typo="5" />
            </div >
            <div style={{ display: "flex", margin: "5px 0" }}>
                <FilterElement typo="Ruby" />
                <FilterElement typo="1" />
                <FilterElement typo="2" />
                <FilterElement typo="3" />
                <FilterElement typo="4" />
                <FilterElement typo="5" />
            </div >
        </div>
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