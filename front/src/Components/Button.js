import { useState } from "react";

const Button = ({ typo, ID, onClick }) => {
    const [mouseOver, setMouseOver] = useState(false);
    const mouseGetOver = () => {
        setMouseOver(true);
    }
    const mouseGetOut = () => {
        setMouseOver(false);
    }

    return (
        <button id={ID}
            style={{
                width: "100%",
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                boxSizing: 'border-box',
                padding: "8px 12px",
                border: 'solid 1px #0083e8',
                borderRadius: "3px",
                background: mouseOver ? '#0083e8' : '#fff',
                color: mouseOver ? '#fff' : '#0083e8',
                fontSize: "16px",
                cursor: "pointer",
                transition: "all 0.2s ease-out"
            }}
            onMouseOver={mouseGetOver}
            onMouseOut={mouseGetOut}
            onClick={onClick}>
            {typo}
        </button>
    )
}

export default Button;