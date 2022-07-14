import "../../../default.css";
import * as Default from "../../../Default";
import * as Styled from "./Styled";
import Header from "../../../Components/Header";
import Pagination from "../../../Components/Pagination";
import { useEffect, useState } from "react";
import CanvasJSReact from "../../../assets/canvasjs.react";
import axios from "axios";
const CanvasJSChart = CanvasJSReact.CanvasJSChart;

const options = {
    toolTip: { shared: true },
    axisY: { suffix: "%" },
    data: [{
        type: "stackedBar100",
        color: "#0000ff",
        name: "ME",
        showInLegend: true,
        indexLabel: "{y}",
        indexLabelFontColor: "white",
        yValueFormatString: "#,###'%'",
        dataPoints: [{ label: "Solved", y: 21 }]
    },
    {
        type: "stackedBar100",
        color: "#ff0000",
        name: "Rival",
        showInLegend: true,
        indexLabel: "{y}",
        indexLabelFontColor: "white",
        yValueFormatString: "#,###'%'",
        dataPoints: [{ label: "Solved", y: 79 }]
    }]
}

const pages = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

// const RivalGraph = ({ options }) => {
//     return (
//         <CanvasJSChart options={options} />
//     )
// }

const RivalComponent = ({ numb, name, rank, totalSolved, weekSolved, tear }) => {
    const [rivalClicked, rivalOnClick] = useState(false)
    const clickRival = () => {
        rivalOnClick(prev => !prev)
    }

    return (
        <div>
            <Styled.Rival style={{
                background: rivalClicked ? "#d5d5d5" : "#fff"
            }}
                onClick={clickRival}>
                <Styled.RivalElementContainer>
                    <Styled.RivalNumb>{numb}</Styled.RivalNumb>
                    <Styled.RivalElement>{name}</Styled.RivalElement>
                    <Styled.RivalElement>{rank}</Styled.RivalElement>
                </Styled.RivalElementContainer>
                <Default.SelectBoxArrow className={rivalClicked ? "rotate" : ""}
                    src="https://icon-library.com/images/dropdown-arrow-icon/dropdown-arrow-icon-16.jpg" />
            </Styled.Rival>
            {rivalClicked ?
                <div>
                    {/* <RivalGraph options={options} /> */}
                    <Styled.RivalDivider />
                    <Styled.RivalDropDown>해결한 문제 수: {totalSolved >= 0 ? "+" : ""}{totalSolved}</Styled.RivalDropDown>
                    <Styled.RivalDivider />
                    <Styled.RivalDropDown>최근 일주일 동안 해결한 문제 수: {weekSolved >= 0 ? "+" : ""}{weekSolved}</Styled.RivalDropDown>
                    <Styled.RivalDivider />
                    <Styled.RivalDropDown>티어 차이: {tear >= 0 ? "+" : ""}{tear}</Styled.RivalDropDown>
                </div>
                : null}
        </div>
    )
}

const Rival = () => {

    const [rivalList, setRivalList] = useState([])

    useEffect(() => {

        axios.defaults.headers.common['Authorization'] = localStorage.getItem("Authorization")
        axios.get("http://localhost:8080/api/v1/user/rivals")
            .then(res => {
                const rivalData = res.data.data;
                setRivalList(rivalData);
            }).catch(e => {
                console.log("err: ", e);
            })
    }, [])

    return (
        <div>
            <Header />
            <Styled.Container>
                <Styled.RivalContainer>
                    <Styled.RivalDivider />
                    {rivalList.length == 0 ?
                        <div>
                            <div style={{
                                width: "100%",
                                height: "60px",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center"
                            }}>
                                등록한 라이벌이 없습니다.
                            </div>
                            <Styled.RivalDivider />
                        </div>
                        : null
                    }
                    {rivalList.map((rival, index) => {
                        <div>
                            <RivalComponent numb={index + 1} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                            <Styled.RivalDivider />
                        </div>
                    })}
                    {rivalList.length > 10 ?
                        <Styled.PaginationContainer>
                            {pages.map(page => <Pagination number={page} />)}
                        </Styled.PaginationContainer>
                        : null}
                </Styled.RivalContainer>
            </Styled.Container>
        </div >
    )
}

export default Rival;