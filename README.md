# 🎯 백발BaekJoon - 백준 문제 랜덤 추천 서비스
![baekjoon_logo](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baekjoon_logo.png?raw=True)

<br>

## 백발백준 링크 : [AWS Fargate 배포 준비 중]

## ⏰ Project 진행기간

2022.06.25 (토) ~ 2022.07.20 

<br>

## ⏚ 백발백준 - 배경

코딩테스트에 합격하기 위해서 매일 매일 백준 사이트에서 알고리즘 문제를 풀며, '그냥 누가 한 문제 추천해주면 안되는건가' 또는 '내가 원하는 유형들 중에서만 추천해줬으면...', '이 문제를 유형을 모르고 풀었다면... 내가 풀 수 있었을까...?' 라는 생각을 해보신 적 있나요?

`백발백준`은 그러한 사람들의 needs를 충족시키기 위해 탄생했습니다. 원하는 유형과 난이도의 백준 문제를 `백발백준`과 함께라면 매일마다 추천받을 수 있습니다. 당신이 원하는 문제를 백발백중으로 맞춰드리는 서비스! 정해진 유형을 알고 푸는게 아닌, 랜덤하게 모르고 푸는 재미! 백발백준에서 찾으실 수 있습니다!

<br>

## ⌗ 백발백준 - 개요

> **원하는 난이도, 유형만 입력하면 랜덤하게 문제를 추천해준다**

백발백준은 위와 같은 간단한 목적 아래 추천받길 원하는 난이도와 문제 유형을 원하는 만큼 고르면 그 중에서 랜덤하게 문제를 추천받을 수 있는 웹 서비스입니다. 

매번 스터디장으로써 문제유형을 보게 되지 않으셨나요? 한 유형에 대해 공부하려고 했으나, '어떤 유형'인지 아는 바람에 풀이의 방향이 고정된적이 있는 사람들을 위해 만들었습니다. 누군가에게 도움을 받거나 의지하지 않아도, 인터넷의 추천 문제 리스트를 다 풀게 되더라도 백발백준만큼은 멈추지 않고 계속 추천을 할 것입니다. 

문제를 풀면서 목표를 세우고, 친구와 라이벌을 맺어 선의의 경쟁도 할 수 있습니다. 여러분들께서 백준 아이디만 갖고 계시다면 바로 가입하여 추천받을 수 있는 웹 서비스! 백발백준입니다.

<br>

## ⌶ 주요 기능

- 문제 추천
  - 문제 난이도별
  - 문제 유형별
  - 사용자가 풀었던 문제는 제외!

- 문제 history (문제 푼 기록)

<br>

## ⎚ 백발백준 서비스 화면

- 회원가입

  ![baek1_1](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baek1_1.png?raw=True)

  ![baek1_2](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baek1_2.png?raw=True)

  ![baek1_3](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baek1_3.png?raw=True)

  테스트를 위해 `rlawowns000`이라는 백준 아이디를 연동하여 계정을 생성.

  ![baek1_4](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baek1_4.png?raw=True)

  회원가입을 하면 `rlawowns000`가 풀었던 문제들을 '푼 문제 리스트'에 저장을 진행. 추천을 할 때에 푼 문제는 제외하고 추천하기 때문이다.

- `TODAY` 추천

  ![baek1_5](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baek1_5.png?raw=True)

  로그인을 하게 되면, 그 날의 추천 문제가 생긴다. 아까 위에서 계정생성 때 세팅한 난이도와 유형을 기반으로 추천된다.

  ![baek1_6](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baek1_6.png?raw=True)

  클릭하면 링크가 아래에 나오고 해당 링크를 클릭하면,

  ![baek1_7](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baek1_7.png?raw=True)

  `rlawowns000`이라는 백준 아이디가 아직 풀지 않은 문제 중에서, 세팅한 난이도와 유형 내에서 랜덤하게 1문제가 추천되는 것을 확인할 수 있다.

  <br>

  기본적으로 추천된 문제를 풀 때까지는 새롭게 추천이 안된다. `refresh` 버튼을 눌러서 새롭게 추천받을 수 있으나 횟수제한이 있다. 날짜가 바뀌면 초기화 된다.

- 추가 추천

  추천받은 문제를 풀면 계속해서 새로운 문제가 추천된다. 

- 추천 새로고침

  그 날의 문제가 마음에 안들면 `refresh` 버튼을 눌러서 새로 추천받을 수 있다. 하루에 최대 3번까지 가능하다.

- 라이벌 화면

  라이벌을 등록하고 확인할 수 있다.

- 마이 페이지

  회원정보 설정을 할 수 있다. 푼 문제를 확인해볼 수도 있다.

<br>

## 💻 주요 기술

### 🧑🏻‍💻 Back-end

- IntelliJ IDE
- `Spring boot 2.7.1`, `Spring Data JPA`, `Spring Security`, `Spring Validation`, `Spring Web`, `MySQL`, `Json simple`
- Java 11

<br>

### 👩🏻‍💻 Front-end

- Vscode
- `React 18.2.0`, `react-dom 18.2.0`, `react-redux 8.0.2`, `react-router-dom 6.3.0`, `react-scripts 5.0.1`, `styled-components 5.3.5`, `axios 0.27.2`, `jquery 3.6.0`
- Javascript

<br>

## 📂 프로젝트 파일 구조

### Back-end

```text
backjoonrecommender
├── common
│   ├── ResponseCodeMessage.java
│   └── ScheduledTasks.java
├── config
│   ├── CorsConfig.java
│   ├── SecurityConfig.java
│   ├── auth
│   └── jwt
├── controller
│   ├── api
│   └── dto
├── entity
├── exception
├── repository
└── service
```

<br>

### Front-end

```text
front
├── public
│   ├── index.html
│   └── manifest.json
├── src
│   ├── client
│   │   └── Root.js
│   ├── Components    
│   │   ├── Button.js
│   │   ├── Header.js
│   │   ├── Pagination.js
│   │   └── Toggle.js
│   ├── Pages
│   │   ├── admin
│   │   │   ├── admin.js
│   │   │   └── adminInfo.json
│   │   ├── community
│   │   │   ├── Login
│   │   │   │   ├── Login.js
│   │   │   │   └── Styled.js
│   │   │   ├── MyPage
│   │   │   │   ├── MyPage.js
│   │   │   │   └── Styled.js
│   │   │   ├── SignUp
│   │   │   │   ├── SignUp.js
│   │   │   │   └── Styled.js
│   │   │   └── Community.js
│   │   └── main
│   │       ├── Recommend
│   │       │   ├── Recommend.js
│   │       │   └── Styled.js
│   │       ├── Rival
│   │       │   ├── Rival.js
│   │       │   └── Styled.js
│   │       └── Solved
│   │           ├── Solved.js
│   │           └── Styled.js
│   ├── shared
│   │   └── App.js
│   ├── default.css
│   ├── Default.js
│   ├── index.js
│   └── reportWebVitals.js
├── package-lock.json
└── package.json
```

<br>

## 🔨 협업 툴

- Git

- Notion - [백발백준 project link](https://www.notion.so/5b48bdd21caa4d12bee492f4b7556e0d)

  - 기획

    ![baek1_8](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baek1_8.png?raw=True)

    기획 단계에서의 api docs정리

  - 일정 관리

    ![baek1_9](https://github.com/xi-jjun/xi-jjun.github.io/blob/master/_posts/aws/img/baek1_9.png?raw=True)

- Slack

- Zoom

<br>

## 📌 협업 환경

- 회의
- Notion

<br>

## 📌 팀원

- 고나연
- 김재준
  - 동국대학교 전자전기공학부
  - 백엔드 담당
  - 백준 문제를 DB에 migration, 문제 추천, 새로고침 API개발

- 홍석주

<br>

## 📌 프로젝트 산출물

- 기능 명세서
- API
- ERD
- convention

