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

const RivalComponent = ({ numb, name, rank, rivalId }) => {
    const [rivalClicked, rivalOnClick] = useState(false)
    const clickRival = () => {
        rivalOnClick(prev => !prev)
    }

    console.log("rival id: ", rivalId);

    const rivalRegister = () => {
        axios.post(`http://localhost:8080/api/v1/user/rivals/${rivalId}`)
            .then(res => {
                console.log("rival registered: ", res);
            }).catch(e => console.log("err: ", e))
    }

    return (
        <div>
            <Styled.Rival style={{
                background: rivalClicked ? "#d5d5d5" : "#fff"
            }}
                onClick={clickRival}>
                <Styled.RivalElementContainer>
                    <Styled.RivalNumb>{numb}</Styled.RivalNumb>
                    <Styled.RivalElement style={{ width: "300px" }}>{name}</Styled.RivalElement>
                    <Styled.RivalElement>{rank}</Styled.RivalElement>
                </Styled.RivalElementContainer>
                <Default.SelectBoxArrow className={rivalClicked ? "rotate" : ""}
                    src="https://icon-library.com/images/dropdown-arrow-icon/dropdown-arrow-icon-16.jpg" />
            </Styled.Rival>
            {rivalClicked ?
                <div>
                    {/* <RivalGraph options={options} /> */}
                    <Styled.RivalDivider />
                    <Styled.RivalDropDown onClick={rivalRegister}>라이벌 등록</Styled.RivalDropDown>
                </div>
                : null}
        </div>
    )
}

const Rival = () => {

    const [rivalList, setRivalList] = useState([])
    const [searchResult, setSearchResult] = useState({});
    const [searchInput, setSearchInput] = useState("");
    const handleSearchInput = (e) => {
        setSearchInput(e.target.value);
    }

    const clickSearch = () => {
        axios.defaults.headers.common['Authorization'] = localStorage.getItem("Authorization")
        axios.post("http://localhost:8080/api/v1/user/rivals", { username: searchInput })
            .then(res => {
                setSearchResult(res.data.data)
            })
            .catch(e => console.log("err: ", e))
    }

    useEffect(() => {
        setSearchResult({});
        axios.defaults.headers.common['Authorization'] = localStorage.getItem("Authorization")
        axios.get("http://localhost:8080/api/v1/user/rivals")
            .then(res => {
                const rivalData = res.data.data;
                console.log("rival data: ", rivalData);
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
                    {searchResult == {} && rivalList.length == 0 ?
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
                    {searchResult == {} && rivalList.map((rival, index) => {
                        <div>
                            <RivalComponent numb={index + 1} name="라이벌" rank="silver2" />
                            <Styled.RivalDivider />
                        </div>
                    })}
                    {searchResult != {} ?
                        <div>
                            <RivalComponent numb={1} name={searchResult.username} rank="silver2" rivalId={searchResult.id} />
                            <Styled.RivalDivider />
                        </div>
                        : null
                    }
                    {rivalList.length > 10 ?
                        <Styled.PaginationContainer>
                            {pages.map(page => <Pagination number={page} />)}
                        </Styled.PaginationContainer>
                        : null}
                </Styled.RivalContainer>
                <div style={{ display: "flex", justifyContent: "center", alignItems: "center", marginTop: "15px" }}>
                    <input style={{
                        width: "430px", height: "40px",
                        border: "none", borderBottom: "solid 1px #666"
                    }} placeholder="search"
                        onChange={handleSearchInput} />
                    <img onClick={clickSearch} style={{ width: "20px", height: "20px", marginTop: "5px" }} src="https://cdn-icons-png.flaticon.com/512/61/61088.png" />
                </div>
            </Styled.Container>
        </div >
    )
}

export default Rival;