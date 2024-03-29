import Header from "../../../Components/Header";
import * as Default from "../../../Default";
import * as Community from "../Community";
import * as Styled from "./Styled";
import Button from "../../../Components/Button";
import { useEffect, useState } from "react";
import axios from "axios";

const MyPage = () => {
  const levels = [];
  const tags = [];

  const [data, setData] = useState(null);
  const [newPw, setNewPw] = useState("");
  const [baekjoonID, setBaekjoonId] = useState("");

  const handleInputNewPw = (e) => {
    setNewPw(e.target.value);
  };

  useEffect(() => {
    // .get("http://localhost:8080/api/v1/user", {
    axios
      .get(process.env.REACT_APP_BASE_URL + "/api/v1/user", {
        headers: {
          "Content-type": "application/x-www-form-urlencoded; charset=UTF-8;",
          Authorization: localStorage.getItem("Authorization"),
        },
      })
      .then((res) => {
        const newData = res.data.data;
        setBaekjoonId(newData.baekJoonId);
        setData(newData);
        // console.log(newData);
        const levels = newData.levels.split(",");
        const dailyTags = newData.dailyTags.split(",");
        for (let l of levels) {
          document.querySelector(`#my-page-${l}`).checked = true;
        }
        for (let t of dailyTags) {
          document.querySelector(`#my-page-daily-element-${t}`).checked = true;
        }
      })
      .catch((e) => {
        console.log("error: ", e);
      });
  }, []);

  const tryConfirm = () => {
    for (let i = 0; i < 31; i++) {
      if (document.querySelector(`#my-page-${i}`).checked) {
        levels.push(i);
      }
    }

    for (let e of document.querySelectorAll(".my-page-daily-element")) {
      if (e.checked) {
        tags.push(e.getAttribute("value"));
      }
    }

    const signUpRequestDTO = {
      username: data.username,
      baekJoonId: baekjoonID,
      password: newPw,
      option: "TODAY",
      levels: levels.join(),
      tags: tags.join(),
    };

    // console.log("sign up request: ", signUpRequestDTO);
    axios.defaults.headers.common["Authorization"] =
      localStorage.getItem("Authorization");
      // .patch("http://localhost:8080/api/v1/user", signUpRequestDTO)
    axios
      .patch(process.env.REACT_APP_BASE_URL + "/api/v1/user", signUpRequestDTO)
      .then((res) => {
        console.log("res: ", res);
      })
      .catch((e) => {
        console.log("err: ", e);
        alert(e.response.data.message);
      });
  };

  return (
    <div>
      <Header />
      <Styled.Container>
        <Community.InfoContainer>
          <Community.InfoContainerLabel>
            Membership Info
          </Community.InfoContainerLabel>
          <Community.InfoSubContainer>
            <Community.InfoLabel>PW</Community.InfoLabel>
            <Community.Input
              placeholder="새로운 pw"
              onChange={handleInputNewPw}
            />
          </Community.InfoSubContainer>
        </Community.InfoContainer>
        <Community.InfoContainer>
          <Community.InfoContainerLabel>Filter</Community.InfoContainerLabel>
          <Community.DifficultyFilter page="my-page" />
          <Community.DailyFilter id="my-page-daily" />
          <div style={{ display: "flex", justifyContent: "center" }}>
            <Default.StyledLink to="/" onClick={tryConfirm}>
              <Button typo="Confirm" />
            </Default.StyledLink>
          </div>
        </Community.InfoContainer>
      </Styled.Container>
    </div>
  );
};

export default MyPage;
