import "../../../default.css";
import * as Styled from './Styled';
import Header from "../../../Components/Header";
import Pagination from "../../../Components/Pagination";
import { useState } from 'react';

const pages = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

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