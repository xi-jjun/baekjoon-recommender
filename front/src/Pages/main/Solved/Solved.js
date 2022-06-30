import "../../../default.css";
import * as Styled from './Styled';
import Header from "../../../Components/Header";
import { useState } from 'react';

const pages = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

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

const Solved = () => {
    return (
        <div>
            <Header />
            <Styled.Container>
                <Styled.QuestionContainer>
                    <Styled.QuestionContainerLabel>날짜</Styled.QuestionContainerLabel>
                    <Styled.Question>
                        <Styled.QuestionElement>22.06.25</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question className='space' />
                    <Styled.Question className='space' />
                    <Styled.Question className='space' />
                    <Styled.Question className='space' />
                    <Styled.Question>
                        <Styled.QuestionElement>22.06.24</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question className='space' />
                    <Styled.Question className='space' />
                    <Styled.Question className='space' />
                    <Styled.Question className='space' />
                </Styled.QuestionContainer>
                <Styled.QuestionContainer>
                    <Styled.QuestionContainerLabel>푼 문제</Styled.QuestionContainerLabel>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.PaginationContainer>
                        {pages.map(page => <Pagination number={page} />)}
                    </Styled.PaginationContainer>
                </Styled.QuestionContainer>
                <Styled.QuestionContainer>
                    <Styled.QuestionContainerLabel>못 푼 문제</Styled.QuestionContainerLabel>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question>
                        <Styled.QuestionElement>10120번</Styled.QuestionElement>
                        <Styled.QuestionElement>벽돌 쌓기</Styled.QuestionElement>
                        <Styled.QuestionElement>silver[2]</Styled.QuestionElement>
                    </Styled.Question>
                    <Styled.Question className='space' />
                    <Styled.Question className='space' />
                    <Styled.Question className='space' />
                    <Styled.Question className='space' />
                </Styled.QuestionContainer>
            </Styled.Container>
        </div>
    )
}

export default Solved;