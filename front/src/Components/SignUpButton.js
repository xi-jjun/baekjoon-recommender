import { react, useState } from "react";

const SignUpButton = ({ typo, ID }) => {
    const buttonClick = () => {
        console.log("button in sign up page clicked")
    }

    return (
        <button
            style={{
                width: "120px",
                height: "40px",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                background: "#8b8b8b",
                border: "solid 1px #8b8b8b",
                boxSizing: "border-box",
                padding: "0 10px",
                marginLeft: "15px",
                color: "#fff",
                cursor: "pointer",
            }}
            onClick={buttonClick}>
            {typo}
        </button>
    )
}

export default SignUpButton;