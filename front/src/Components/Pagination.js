import { useState } from "react"

const Pagination = ({ number }) => {
    const [pageClicked, pageOnClick] = useState(false)
    const clickPage = () => {
        pageOnClick(prev => !prev)
    }

    const [onMouseOver, setMouseOver] = useState(false)
    const getMouseOver = () => {
        setMouseOver(true)
    }
    const getMouseOut = () => {
        setMouseOver(false)
    }

    return (
        <div style={{
            width: "30px",
            height: "30px",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            background: pageClicked || onMouseOver ? "#0083e8" : "#fff",
            boxSizing: "border-box",
            borderRight: "solid 1px #0083e8",
            color: pageClicked || onMouseOver ? "#fff" : "#0083e8",
            fontWeight: pageClicked || onMouseOver ? 600 : 400,
            cursor: "pointer",
            transition: "all 0.1s ease-out"
        }}
            onClick={clickPage}
            onMouseOver={getMouseOver}
            onMouseOut={getMouseOut}>
            {number}
        </div>
    )
}

export default Pagination;