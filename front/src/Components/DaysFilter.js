import * as Default from "../Default";
import { useState } from "react";
import SelectBox from "./SelectBox";
import "../default.css"

const DaysFilter = () => {

    const days = ["월", "화", "수", "목", "금", "토", "일"]

    const DayFilterElement = ({ typo }) => {

        const [onMouseOver, setMouseOver] = useState(false)
        const getMouseOver = () => {
            setMouseOver(true)
        }
        const getMouseOut = () => {
            setMouseOver(false)
        }

        const [dayFilter, dayFilterOnClick] = useState(false);
        const clickDayFilter = () => {
            dayFilterOnClick((prev) => !prev)
            const dropdown = document.querySelector(`.dropdown_${typo}`)
            if (dayFilter) dropdown.classList.add("hidden")
            else dropdown.classList.remove("hidden")
        }

        return (
            <Default.DaysFilter
                style={{
                    background: dayFilter || onMouseOver ? "#0083e8" : "#fff",
                    color: dayFilter || onMouseOver ? "#fff" : "#0083e8",
                }}
                onClick={clickDayFilter}
                onMouseOver={getMouseOver}
                onMouseOut={getMouseOut}
                id={typo}>
                {typo}
            </Default.DaysFilter>
        )
    }

    const questionTypeOptions = ["dp", "brute force", "sort"]
    const difficultyGradeOptions = ["bronze", "silver", "gold", "platinum", "diamond", "ruby"]
    const difficultyLevelOptions = [1, 2, 3, 4, 5]

    const DropDown = ({ typo }) => {

        return (
            <div className={`dropdown_${typo} hidden`} >
                <Default.SelectBoxContainer >
                    <div style={{
                        display: "flex",
                        alignItems: "center",
                        boxSizing: "border-box",
                        marginRight: "10px",
                        color: "#333",
                        fontSize: "16px",
                        fontWeight: 600
                    }}>{typo}</div>
                    <SelectBox selectTypo="문제 유형" options={questionTypeOptions} ></SelectBox>
                </Default.SelectBoxContainer >
            </div >
        )
    }

    return (
        <div>
            <Default.DaysFilterContainer>
                {days.map(day => <DayFilterElement typo={day} />)}
            </Default.DaysFilterContainer>
            {days.map(day => <DropDown typo={day} />)}
        </div>
    )
}

export default DaysFilter;