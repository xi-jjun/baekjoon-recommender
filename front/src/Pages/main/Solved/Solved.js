import "../../../default.css";
import * as Styled from './Styled';
import Header from "../../../Components/Header";
import Pagination from "../../../Components/Pagination";
import { useState, useEffect } from 'react';
import axios from "axios";

const pages = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

const Solved = () => {

    useEffect(() => {
        axios.get("http://localhost:8080/api/v1/user", {
            headers: {
                'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
                'Authorization': localStorage.getItem("Authorization")
            }
        }).then(res => {
            console.log("res ", res);
        }).catch(e => {
            console.log("error: ", e);
        })
    })

    return (
        <div>
            <Header />
            <Styled.Container>
                <Styled.QuestionContainer>
                    <Styled.QuestionLabelContainer>
                        <Styled.QuestionLabel>날짜</Styled.QuestionLabel>
                    </Styled.QuestionLabelContainer>
                    <Styled.Question>
                        <Styled.QuestionElement>22.06.25</Styled.QuestionElement>
                    </Styled.Question>
                </Styled.QuestionContainer>
            </Styled.Container>
        </div>
    )
}

export default Solved;