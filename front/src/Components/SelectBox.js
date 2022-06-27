import { react, useState } from "react";
import * as Default from "../Default";

const SelectBox = ({ selectTypo, options }) => {
    const [selectClicked, selectOnClick] = useState(false);
    const clickSelectBox = () => {
        selectOnClick(selectClicked => !selectClicked)
    }
    const OptionElement = ({ optionTypo }) => {
        const [optionClicked, optionOnClick] = useState(null)
        const clickOption = (value) => {
            optionOnClick(value);
            selectOnClick()
            console.log(optionClicked)
        }

        return (
            <div style={{
                width: "100%",
                height: "38px",
                display: "flex",
                alignItems: "center",
                background: "#fff",
                borderBottom: "solid 1px #666",
                boxSizing: "border-box",
                padding: "0 10px",
                color: "#666",
                cursor: "pointer",
                zIndex: 1
            }}
                onClick={clickOption}
                name="option"
                value={optionClicked}>
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
                onClick={clickSelectBox}>
                <span>{selectTypo}</span>
                <Default.SelectBoxArrow src="https://icon-library.com/images/dropdown-arrow-icon/dropdown-arrow-icon-16.jpg" />
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