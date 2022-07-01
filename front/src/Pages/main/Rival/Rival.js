import "../../../default.css";
import * as Default from "../../../Default";
import * as Styled from "./Styled";
import Header from "../../../Components/Header";
import Pagination from "../../../Components/Pagination";
import { useState } from "react";
import CanvasJSReact from "../../../assets/canvasjs.react";
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

const RivalGraph = ({ options }) => {
    return (
        <CanvasJSChart options={options} />
    )
}

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
                    <RivalGraph options={options} />
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
    return (
        <div>
            <Header />
            <Styled.Container>
                <Styled.RivalContainer>
                    <Styled.RivalDivider />
                    <RivalComponent numb={1} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <RivalComponent numb={2} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <RivalComponent numb={3} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <RivalComponent numb={4} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <RivalComponent numb={5} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <RivalComponent numb={6} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <RivalComponent numb={7} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <RivalComponent numb={8} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <RivalComponent numb={9} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <RivalComponent numb={10} name="라이벌" rank="silver2" totalSolved={+99} weekSolved={+51} tear={-3} />
                    <Styled.RivalDivider />
                    <Styled.PaginationContainer>
                        {pages.map(page => <Pagination number={page} />)}
                    </Styled.PaginationContainer>
                </Styled.RivalContainer>
            </Styled.Container>
        </div>
    )
}

export default Rival;