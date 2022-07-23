import "../../../default.css";
import * as Styled from './Styled';
import Header from "../../../Components/Header";
import { useState, useEffect } from 'react';
import axios from "axios";

const QuestionDate = ({ date }) => {
    return (
        <Styled.QuestionElement>
            {date == "2022-06-25" ? "회원가입 전" : date}
        </Styled.QuestionElement>
    )
}

const QuestionInfo = ({ id, title, level }) => {
    return (<div style={{ display: "flex" }}
        onClick={() => {
            window.open(`https://www.acmicpc.net/problem/${id}`, "_blank");
        }}>
        <Styled.QuestionElement style={{
            width: "60px"
        }}>
            {id}
        </Styled.QuestionElement>
        <Styled.QuestionElement style={{
            width: "370px",
            paddingLeft: "30px"
        }}>
            {title}
        </Styled.QuestionElement>
        <Styled.QuestionElement style={{
            width: "30px"
        }}>
            {level}
        </Styled.QuestionElement>
    </div>)
}

const Solved = () => {

    const [questionList, setQuestionList] = useState([]);
    const [unSolvedQuestionList, setUnSolvedQuestionList] = useState([]);
    const [pages, setPages] = useState([]);
    const [checked, setChecked] = useState(0);

    useEffect(() => {

        axios.get("http://localhost:8080/api/v1/user", {
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
                'Authorization': localStorage.getItem("Authorization")
            }
        }).then(res => {
            const userId = res.data.data.userId;
            console.log("userid: ", userId);
            axios.get(`http://localhost:8080/api/v1/user/${userId}/solved`, {
                headers: {
                    "Authorization": localStorage.getItem("Authorization")
                }
            }).then(res => {
                const solvedData = res.data.data;
                setQuestionList(solvedData);
                setPages([...Array(parseInt(solvedData.length / 12) + 1).keys()].map((page, index) => {
                    if (index == checked) {
                        console.log("checked: ", checked);
                        return (
                            <Pagination number={page + 1} checked={true} />
                        )
                    }
                    else {
                        return (
                            <Pagination number={page + 1} checked={false} />
                        )
                    }
                }))
            }).catch(e => console.log("err: ", e));
        }).catch(e => console.log("err: ", e));

        axios.defaults.headers.common['Authorization'] = localStorage.getItem("Authorization");
        axios.get("http://localhost:8080/api/v1/recommendation/today")
            .then(res => {
                setUnSolvedQuestionList(res.data.data);
            }).catch(e => console.log("err: ", e));

    }, [checked])
    // checked 값이 바뀔 때마다 필터 클릭이 훨씬 느리게 됨

    const Pagination = ({ number, checked }) => {
        const clickPage = () => {
            setChecked(number - 1)
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
                background: checked || onMouseOver ? "#0083e8" : "#fff",
                boxSizing: "border-box",
                borderRight: "solid 1px #0083e8",
                color: checked || onMouseOver ? "#fff" : "#0083e8",
                fontWeight: checked || onMouseOver ? 600 : 400,
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

    return (
        <div>
            <Header />
            <Styled.Container>
                <Styled.QuestionContainer>
                    <Styled.QuestionLabelContainer>
                        <Styled.QuestionLabel>날짜</Styled.QuestionLabel>
                        <Styled.QuestionLabel>푼 문제</Styled.QuestionLabel>
                    </Styled.QuestionLabelContainer>
                    {!questionList || questionList.length == 0 ?
                        <div style={{
                            width: "100%",
                            height: "60px",
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "center"
                        }}>
                            푼 문제가 없습니다.
                        </div>
                        : null}
                    {questionList && questionList.slice(checked * 12, (checked + 1) * 12).map(q =>
                        <Styled.Question>
                            <QuestionDate date={q.solvedDate.substring(0, 10)} />
                            <QuestionInfo
                                id={q.problem.id}
                                title={q.problem.title}
                                level={q.problem.level} />
                        </Styled.Question>
                    )}
                    {pages.length > 0 ?
                        <Styled.PaginationContainer>
                            {pages}
                        </Styled.PaginationContainer>
                        : null}
                </Styled.QuestionContainer>
                <Styled.QuestionUnSolvedContainer>
                    <Styled.QuestionLabelContainer>
                        <Styled.QuestionLabel>못 푼 문제</Styled.QuestionLabel>
                    </Styled.QuestionLabelContainer>
                    {!unSolvedQuestionList || unSolvedQuestionList.length == 0 ?
                        <div style={{
                            width: "100%",
                            height: "60px",
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "center"
                        }}>
                            못 푼 문제가 없습니다.
                        </div>
                        : null}
                    {unSolvedQuestionList && unSolvedQuestionList.map(q =>
                        <Styled.Question>
                            <QuestionInfo
                                id={q.problem.id}
                                title={q.problem.title}
                                level={q.problem.level} />
                        </Styled.Question>)
                    }
                </Styled.QuestionUnSolvedContainer>
            </Styled.Container>
        </div>
    )
}

export default Solved;