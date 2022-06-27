import * as Styled from "../Pages/community/SignUp/Styled";
import { useState } from "react";

const DaysFilter = ({ typo }) => {
    const [dayFilter, dayFilterOnClick] = useState(false);
    const clickDayFilter = () => {
        dayFilterOnClick((prev) => !prev)
    }
    try {
        if (dayFilter) document.querySelector(`#${typo}`).classList.remove("hidden")
        else {
            document.querySelector(`#${typo}`).classList.add("hidden")
        }
    }
    catch { console.log("error") }

    return (
        <Styled.DaysFilter
            style={{
                background: dayFilter ? "#0083e8" : "#fff",
                color: dayFilter ? "#fff" : "#0083e8",
            }}
            onClick={clickDayFilter}>
            {typo}
        </Styled.DaysFilter>
    )
}

export default DaysFilter;