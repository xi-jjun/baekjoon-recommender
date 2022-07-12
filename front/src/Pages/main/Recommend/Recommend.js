import React, { useEffect, useState } from "react";
import * as Default from "../../../Default";
import * as Styled from "./Styled"
import { DifficultyFilter, FilterElement } from "../../community/Community";
import Header from "../../../Components/Header";
import Button from "../../../Components/Button";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const questionTypeOptions = ["dp", "brute force", "sort"]

const Question = ({ number, title, solved }) => {
    const [dropBoxClicked, dropBoxOnClick] = useState(false)
    const clickDropBox = () => {
        dropBoxOnClick((prev) => !prev)
    }
    const ShortCut = () => {
        return (
            <Styled.Question>
                <Styled.QuestionShortCut to="https://www.acmicpc.net/" target="_blank">{number}번-{title} baekJoon 문제 바로 가기</Styled.QuestionShortCut>
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

const Recommend = () => {
    const navigate = useNavigate();
    const toLogin = () => {
        navigate("/user/login", { replace: true });
    }

    const [problemId, setProblemId] = useState(-1);

    const checkIfSolved = () => {
        // 새로고침 하면 추천한 문제가 풀렸는지 확인
        axios.post("http://localhost:8080/api/v1/recommendation",
            {
                problemId: problemId
            },
            {
                headers: {
                    'Content-type': 'application/json; charset=UTF-8',
                    'Authorization': localStorage.getItem("Authorization"),
                },
            }).then(res => {
                console.log("res: ", res);
            }).catch(e => console.log("err: ", e));
    }

    const [data, setData] = useState([])

    useEffect(() => {

        if (!localStorage.getItem("Authorization")) {
            console.log("navigate to login");
            toLogin();
        }

        // if (window.performance) {
        //     if (performance.navigation.TYPE_RELOAD) {
        //         // 새로고침 여부 확인
        //         checkIfSolved();
        //     }
        // }

        setTimeout(() => {
            // 로그인 request 처리 후 recommend 페이지의 request

            axios.patch("http://localhost:8080/api/v1/system/problem-list", {
                headers: {
                    "Authorization": localStorage.getItem("Authorization")
                }
            }).then(res => {
                console.log("Res: ", res);
                // 문제 추천 받기
                axios.get("http://localhost:8080/api/v1/recommendation", {
                    headers: {
                        "Authorization": localStorage.getItem("Authorization")
                    }
                })
                    .then((res) => {
                        setData(res.data);
                        console.log(data)
                        setProblemId(data.problemId)
                    })
                    .catch((e) => console.log("recommend err: ", e))
            }).catch(console.log("not admin"));
        }, 300);

    }, []);

    const clickConfirm = () => {
        console.log("confirm button clicked")
        axios.get("http://localhost:8080/api/v1/recommendation/reload", {
            headers: {
                "Authorization": localStorage.getItem("Authorization")
            }
        }).then((res) => {
            console.log("res: ", res)

        }).catch((e) => console.log("err: ", e))
    }

    const [buttonClicked, buttonOnClick] = useState(false)
    const clickButton = () => {
        buttonOnClick(prev => !prev)
    }

    const [checkBoxClicked, checkBoxOnClick] = useState(true)
    const clickCheckBox = () => {
        checkBoxOnClick(prev => !prev)
    }

    const nextProblem = () => {

        const levels = [];
        for (let i = 0; i < 31; i++) {
            if (document.querySelector(`#recommend-${i}`).checked) {
                levels.push(i);
            }
        }

        const tags = []
        for (let e of document.querySelectorAll("#recommend-type-element")) {
            if (e.checked) {
                tags.push(e.getAttribute("value"))
            }
        }

        const settingRequestDTO = {
            option: checkBoxClicked ? "TEMP" : "",
            levels: levels.join(),
            tags: tags.join(),
            // 아래는 안쓰는 데이터이다.
            sun: "",
            mon: "",
            tue: "",
            wed: "",
            thu: "",
            fri: "",
            sat: ""
        }
        console.log("info: ", settingRequestDTO);

        axios.post("http://localhost:8080/api/v1/recommendation/additional", settingRequestDTO,
            {
                headers: {
                    "Authorization": localStorage.getItem("Authorization")
                }
            }).then(res => {
                console.log("res: ", res);
            }).catch(e => console.log("err: ", e));
    }

    const AddQuestionButton = () => {

        const [mouseOver, setMouseOver] = useState(false)
        const getMouseOver = () => {
            setMouseOver(true)
        }
        const getMouseOut = () => {
            setMouseOver(false)
        }


        const AdditionalFilter = () => {
            return (
                <div>
                    <Styled.FilterCheckBoxContainer>
                        <input type="checkbox"
                            style={{ width: "12px", height: "12px", borderColor: "#e5e5e5" }} checked={checkBoxClicked} onChange={clickCheckBox} />
                        <Styled.FilterCheckBoxLabel>필터 사용</Styled.FilterCheckBoxLabel>
                    </Styled.FilterCheckBoxContainer>
                    {checkBoxClicked ?
                        <div>
                            <div style={{ display: "flex", margin: "25px 0" }}>
                                <div style={{ width: "130px" }}>문제 유형</div>
                                {questionTypeOptions.map(op => <FilterElement typo={op} id="recommend-type-element" />)}
                            </div >
                            <DifficultyFilter page="recommend" />
                        </div> : null}
                    <Styled.AdditionalQuestionButtonWrapper>
                        <Styled.AdditionalQuestionButtonContainer>
                            <Default.StyledLink onClick={nextProblem} to="/"><Button typo="Confirm" ID="additional_question" /></Default.StyledLink>
                        </Styled.AdditionalQuestionButtonContainer>
                    </Styled.AdditionalQuestionButtonWrapper>
                </div >
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
                    transition: "all 0.5s ease-out",
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
                <div>
                    <Styled.AdditionalQuestionForm>
                        <AddQuestionButton>+</AddQuestionButton>
                    </Styled.AdditionalQuestionForm>
                </div>
            </Styled.Container>
        </div>
    )
}

export default Recommend;