import { useState } from "react";
import * as Default from "../../../Default";
import * as Styled from "./Styled"
import Header from "../../../Components/Header";

const Question = ({ number, title, solved }) => {
    const [dropBoxClicked, dropBoxOnClick] = useState(false)
    const clickDropBox = () => {
        dropBoxOnClick((prev) => !prev)
    }
    const ShortCut = () => {
        return (
            <Styled.Question>
                <Styled.QuestionShortCut to="https://www.acmicpc.net/" target="_blank">10012번-다이나믹 프로그래밍 baekJoon 문제 바로 가기</Styled.QuestionShortCut>
            </Styled.Question>
        )
    }
    return (
        <div>
            <div style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                boxSizing: "border-box",
                padding: "10px 0",
                background: dropBoxClicked ? "#e5e5e5" : "#fff",
                cursor: "pointer"
            }}
                onClick={clickDropBox}>
                <Styled.QuestionElement>{number}</Styled.QuestionElement>
                <Styled.QuestionElement>{title}</Styled.QuestionElement>
                <Styled.QuestionElement>{solved}</Styled.QuestionElement>
            </div>
            {dropBoxClicked ? <ShortCut /> : null}
        </div>
    )
}

const AddQuestionButton = () => {
    const [mouseOver, setMouseOver] = useState(false)
    const getMouseOver = () => {
        setMouseOver(true)
    }
    const getMouseOut = () => {
        setMouseOver(false)
    }

    const [buttonClicked, buttonOnClick] = useState(false)
    const clickButton = () => {
        buttonOnClick(prev => !prev)
    }

    const AdditionalFilter = () => {
        return (
            <div>this is additionalFilter</div>
        )
    }

    return (
        <div>
            <button style={{
                width: "60px",
                height: "38px",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                background: mouseOver || buttonClicked ? "#ff0000" : "#fff",
                border: "solid 1px #ff0000",
                borderRadius: "3px",
                boxSizing: "border-box",
                margin: "15px 0",
                color: mouseOver || buttonClicked ? "#fff" : "#ff0000",
                fontSize: "20px",
                fontWeight: "400",
                cursor: "pointer"
            }}
                onMouseOver={getMouseOver}
                onMouseOut={getMouseOut}
                onClick={clickButton}>
                +
            </button>
            {buttonClicked ? <AdditionalFilter /> : null}
        </div>
    )
}

const Recommend = () => {
    return (
        <div>
            <Header />
            <Styled.Container>
                <Styled.QuestionForm>
                    <Styled.QuestionFormTypo>Recommended</Styled.QuestionFormTypo>
                    <Styled.QuestionTable>
                        <Styled.Question>
                            <Styled.QuestionElement>No.</Styled.QuestionElement>
                            <Styled.QuestionElement>Type</Styled.QuestionElement>
                            <Styled.QuestionElement>Solved</Styled.QuestionElement>
                        </Styled.Question>
                        <div>
                            <Styled.QuestionTableDivider />
                            <Question number="10012" title="다이나믹 프로그래밍" solved="O" />
                        </div>
                        <div>
                            <Styled.QuestionTableDivider />
                            <Question number="25051" title="다익스트라 알고리즘" solved="X" />
                        </div>
                    </Styled.QuestionTable>
                </Styled.QuestionForm>
                <Styled.AdditionalQuestionForm>
                    <AddQuestionButton>+</AddQuestionButton>
                </Styled.AdditionalQuestionForm>
            </Styled.Container>
        </div>
    )
}

export default Recommend;