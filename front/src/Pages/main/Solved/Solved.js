import "../../../default.css";
import * as Styled from './Styled';
import Header from "../../../Components/Header";
import Pagination from "../../../Components/Pagination";
import { useState, useEffect } from 'react';
import axios from "axios";

const pages = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

const Solved = () => {

    const [questionList, setQuestionList] = useState([]);

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
                setQuestionList(res.data.data);
                console.log("question list: ", questionList);
            }).catch(e => console.log("err: ", e));
        }).catch(e => console.log("err: ", e));

    }, [])

    const QuestionDate = ({ date }) => {
        <Styled.QuestionElement>
            {date}
        </Styled.QuestionElement>
    }

    const QuestionInfo = ({ id, title, level }) => {
        <div style={{ display: "flex" }}>
            <Styled.QuestionElement>
                {id}
            </Styled.QuestionElement>
            <Styled.QuestionElement>
                {title}
            </Styled.QuestionElement>
            <Styled.QuestionElement>
                {level}
            </Styled.QuestionElement>
        </div>
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
                    {questionList && questionList.map(q =>
                        <Styled.Question>
                            <QuestionDate date={q.solvedDate} />
                            <QuestionInfo
                                id={q.problem.id}
                                title={q.problem.title}
                                level={q.problem.level} />
                        </Styled.Question>)
                    }
                </Styled.QuestionContainer>
                <Styled.QuestionUnSolvedContainer>
                    <Styled.QuestionLabelContainer>
                        <Styled.QuestionLabel>못 푼 문제</Styled.QuestionLabel>
                    </Styled.QuestionLabelContainer>
                    {!questionList || questionList.length == 0 ?
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
                    {questionList && questionList.map(q =>
                        <Styled.Question>
                            <QuestionDate date={"22.06.25"} />
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