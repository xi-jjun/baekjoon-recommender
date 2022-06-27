import { react, useState } from "react";

const Toggle = ({ typo1, typo2, element1, element2 }) => {
    const [toggle, setToggle] = useState(true);
    const clickedToggle = () => {
        setToggle((prev) => !prev);
    }
    return (
        <div style={{ width: "100%" }}>
            <div style={{
                maxWidth: "163px",
                height: "30px",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                border: "solid 1px #0083e8",
                borderRadius: "2px",
                boxSizing: "border-box",
                margin: "10px 0",
            }}
                onClick={clickedToggle}>
                <div style={{
                    minWidth: "80px",
                    maxWidth: "80px",
                    height: "100%",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    background: toggle ? "#0083e8" : "#fff",
                    color: "#fff",
                    transition: "all 0.5s ease-in-out",
                }}>
                    {typo1}
                </div>
                <div style={{
                    minWidth: "1px",
                    maxWidth: "1px",
                    height: "100%",
                    background: "#0083e8",
                }}></div>
                <div style={{
                    minWidth: "80px",
                    maxWidth: "80px",
                    height: "100%",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    background: toggle ? "#fff" : "#0083e8",
                    color: "#fff",
                    transition: "all 0.5s ease-in-out"
                }}>
                    {typo2}
                </div>
            </div>
            {toggle ? element1 : element2}
        </div>
    )
}

export default Toggle;