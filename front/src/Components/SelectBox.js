import { react, useState } from "react";
import "../default.css";
import * as Default from "../Default";

const SelectBox = ({ selectTypo, options }) => {
    const [selectClicked, selectOnClick] = useState(false);
    const clickSelectBox = () => {
        selectOnClick(prev => !prev)
    }

    const [optionClicked, optionOnClick] = useState("")

    const OptionElement = ({ optionTypo }) => {
        const [onMouseOver, setMouseOver] = useState(false)
        const getMouseOver = () => {
            setMouseOver(true)
        }
        const getMouseOut = () => {
            setMouseOver(false)
        }

        const clickOption = () => {
            optionOnClick(optionTypo);
            clickSelectBox()
        }

        return (
            <div style={{
                width: "100%",
                height: "38px",
                display: "flex",
                alignItems: "center",
                background: onMouseOver ? "#d5d5d5" : "#fff",
                borderBottom: "solid 1px #666",
                boxSizing: "border-box",
                padding: "0 10px",
                color: "#666",
                cursor: "pointer",
                transition: "all 0.3s ease-out",
                zIndex: 1
            }}
                onClick={clickOption}
                onMouseOver={getMouseOver}
                onMouseOut={getMouseOut}
                name="option"
                value={optionTypo}>
                {optionTypo}</div>
        )
    }
    return (
        <div className="target"
            style={{
                width: "160px",
                margin: "0 10px",
            }}>
            <div style={{
                width: "100%",
                height: "38px",
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                border: "solid 1px #e5e5e5",
                boxSizing: "border-box",
                padding: "0 10px",
                color: "#666",
                fontSize: "14px",
                cursor: "pointer"
            }}
                value={optionClicked}
                onClick={clickSelectBox}>
                <span>{optionClicked ? optionClicked : selectTypo}</span>
                <Default.SelectBoxArrow className={selectClicked ? "rotate" : ""}
                    src="https://icon-library.com/images/dropdown-arrow-icon/dropdown-arrow-icon-16.jpg" />
            </div>
            {selectClicked ?
                <div style={{
                    width: "100%",
                    display: "flex",
                    flexDirection: "column",
                    border: "solid 1px #666",
                    borderBottom: "none",
                    color: "#666"
                }}>
                    {options.map(typo => <OptionElement optionTypo={typo} />)}
                </div>
                : null}
        </div>
    )
}

export default SelectBox;