import "../../../default.css";
import * as Default from "../../../Default";
import * as Styled from "./Styled";
import Header from "../../../Components/Header";
import { useEffect, useState } from "react";
import axios from "axios";

const Rival = () => {
  const [rivalList, setRivalList] = useState([]);
  const [searchResult, setSearchResult] = useState({});
  const [searchInput, setSearchInput] = useState("");
  const handleSearchInput = (e) => {
    setSearchInput(e.target.value);
  };
  const [status, setStatus] = useState("INITIAL");

  const RivalComponent = ({ numb, name, rivalId, registered }) => {
    const [rivalClicked, rivalOnClick] = useState(false);
    const clickRival = () => {
      rivalOnClick((prev) => !prev);
    };

    const rivalRegister = () => {
      axios
        .post(`http://localhost:8080/api/v1/user/rivals/${rivalId}`)
        .then(() => {
          alert("rival registered");
          window.location.reload();
        })
        .catch((e) => console.log("err: ", e));
    };

    const rivalDelete = () => {
      axios
        .delete(`http://localhost:8080/api/v1/user/rivals/${rivalId}`)
        .then(() => {
          alert("rival deleted");
          window.location.reload();
        })
        .catch((e) => console.log("err: ", e));
    };

    return (
      <div>
        <Styled.Rival
          style={{
            background: rivalClicked ? "#d5d5d5" : "#fff",
          }}
          onClick={clickRival}
        >
          <Styled.RivalElementContainer>
            <Styled.RivalNumb>{numb}</Styled.RivalNumb>
            <Styled.RivalElement style={{ width: "300px" }}>
              {name}
            </Styled.RivalElement>
          </Styled.RivalElementContainer>
          <Default.SelectBoxArrow
            className={rivalClicked ? "rotate" : ""}
            src="https://icon-library.com/images/dropdown-arrow-icon/dropdown-arrow-icon-16.jpg"
          />
        </Styled.Rival>
        {rivalClicked ? (
          registered ? (
            <div>
              <Styled.RivalDivider />
              <Styled.RivalDropDown onClick={rivalDelete}>
                라이벌 삭제
              </Styled.RivalDropDown>
            </div>
          ) : (
            <div>
              <Styled.RivalDivider />
              <Styled.RivalDropDown onClick={rivalRegister}>
                라이벌 등록
              </Styled.RivalDropDown>
            </div>
          )
        ) : null}
      </div>
    );
  };

  const clickSearch = () => {
    axios.defaults.headers.common["Authorization"] =
      localStorage.getItem("Authorization");
    setStatus("LOADING");
    axios
      .post("http://localhost:8080/api/v1/user/rivals", {
        username: searchInput,
      })
      .then((res) => {
        if (res.status !== 200) {
          setStatus("SEARCH");
        } else {
          setSearchResult(res.data.data);
          setStatus("NONE");
        }
        console.log(searchResult);
      })
      .catch((e) => {
        console.log("err: ", e);
        alert(e.response.data.message);
      });
  };

  useEffect(() => {
    axios.defaults.headers.common["Authorization"] =
      localStorage.getItem("Authorization");
    axios
      .get("http://localhost:8080/api/v1/user/rivals")
      .then((res) => {
        const rivalData = res.data.data;
        console.log("rival data: ", rivalData);
        setRivalList(rivalData);
      })
      .catch((e) => {
        console.log("err: ", e);
      });
  }, []);

  return (
    <div>
      <Header />
      <Styled.Container>
        <Styled.RivalContainer>
          <Styled.RivalDivider />
          {status !== "NONE" && rivalList.length == 0 ? (
            <div>
              <div
                style={{
                  width: "100%",
                  height: "60px",
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                }}
              >
                등록한 라이벌이 없습니다.
              </div>
              <Styled.RivalDivider />
            </div>
          ) : null}
          {status !== "NONE" && rivalList.length > 0
            ? rivalList.map((rival, index) => (
                <div>
                  <RivalComponent
                    numb={index + 1}
                    name={rival.username}
                    rivalId={rival.id}
                    registered={true}
                  />
                  <Styled.RivalDivider />
                </div>
              ))
            : null}
          {status === "NONE" && (
            // JSON.stringify(searchResult)!=JSON.stringify({})
            // 객체는 주소 값을 저장함,,,
            // ?. 연산자도 사용 가능
            <div>
              <RivalComponent
                numb={1}
                name={searchResult.username}
                rivalId={searchResult.id}
                registered={false}
              />
              <Styled.RivalDivider />
            </div>
          )}
        </Styled.RivalContainer>
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            marginTop: "15px",
          }}
        >
          <input
            style={{
              width: "430px",
              height: "40px",
              border: "none",
              borderBottom: "solid 1px #666",
            }}
            placeholder="search"
            onChange={handleSearchInput}
          />
          <img
            onClick={clickSearch}
            style={{
              width: "20px",
              height: "20px",
              marginTop: "5px",
              cursor: "pointer",
            }}
            src="https://cdn-icons-png.flaticon.com/512/61/61088.png"
          />
        </div>
      </Styled.Container>
    </div>
  );
};

export default Rival;
