const CommunityButton = ({ typo, ID }) => {
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
            id={ID}>
            {typo}
        </button >
    )
}

export default CommunityButton;