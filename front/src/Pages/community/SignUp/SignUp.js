import * as Community from "../Community";
import * as Styled from "./Styled";
import Button from "../../../Components/Button";
import Toggle from "../../../Components/Toggle";
import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function SignUp() {
  const navigate = useNavigate();

  const toLogin = () => {
    navigate("/user/login", { replace: true });
  };

  const toMain = () => {
    navigate("/", { replace: true });
  };

  const [id, setId] = useState("");
  const [pw, setPw] = useState("");
  const [baekjoonId, setBaekjoonId] = useState("");

  const handleInputId = (e) => {
    setId(e.target.value);
  };
  const handleInputPw = (e) => {
    setPw(e.target.value);
  };
  const handleInputBaekjoonId = (e) => {
    setBaekjoonId(e.target.value);
  };

  const trySignUp = () => {
    const levels = [];
    for (let i = 0; i < 31; i++) {
      if (document.querySelector(`#sign-up-${i}`).checked) {
        levels.push(i);
      }
    }

    const tags = [];
    for (let e of document.querySelectorAll(`.sign-up-daily-element`)) {
      if (e.checked) {
        tags.push(e.getAttribute("value"));
      }
    }

    const signUpRequestDTO = {
      username: id,
      baekJoonId: baekjoonId,
      password: pw,
      option: "TODAY",
      levels: levels.join(),
      tags: tags.join(),
    };

    console.log(signUpRequestDTO);

    axios
      .post("http://localhost:8080/api/v1/user", signUpRequestDTO)
      .then((res) => {
        console.log("data: ", res.data);
        console.log("sign up success");
        toMain();
      })
      .catch((e) => {
        console.log("err: ", e);
        alert("sign up failed");
        toLogin();
      });
  };

  return (
    <Styled.Container>
      <Styled.SignUpTypo>Sign Up</Styled.SignUpTypo>
      <Community.InfoContainer>
        <Community.InfoContainerLabel>기본 정보</Community.InfoContainerLabel>
        <Community.InfoSubContainer>
          <Community.InfoLabel>ID</Community.InfoLabel>
          <Community.Input
            placeholder="id"
            id="new_id"
            type="text"
            onChange={handleInputId}
          />
        </Community.InfoSubContainer>
        <Community.InfoSubContainer>
          <Community.InfoLabel>PW</Community.InfoLabel>
          <Community.Input
            placeholder="pw"
            id="new_pw"
            type="password"
            onChange={handleInputPw}
          />
        </Community.InfoSubContainer>
      </Community.InfoContainer>
      <Community.InfoContainer>
        <Community.InfoContainerLabel>
          BaekJoon 정보
        </Community.InfoContainerLabel>
        <Community.InfoSubContainer>
          <Community.InfoLabel>BaekJoon ID</Community.InfoLabel>
          <Community.ShortInput
            placeholder="백준 id"
            id="baekjoon_id"
            type="text"
            onChange={handleInputBaekjoonId}
          />
        </Community.InfoSubContainer>
        <Styled.BaekjoonLink
          replace
          to={{ pathname: "https://www.acmicpc.net/" }}
          target="_blank"
        >
          baekjoon online judge 바로 가기
        </Styled.BaekjoonLink>
      </Community.InfoContainer>
      <Community.InfoContainer>
        <Community.InfoContainerLabel>Filter</Community.InfoContainerLabel>
        <Community.DifficultyFilter page="sign-up" />
        <Community.DailyFilter id="sign-up-daily" />
      </Community.InfoContainer>
      <Button onClick={trySignUp} typo="Sign Up" />
    </Styled.Container>
  );
}

export default SignUp;
