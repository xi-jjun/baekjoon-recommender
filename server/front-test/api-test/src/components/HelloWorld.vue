<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    
    <h1>API TEST</h1>
    <h2>결과는 console log 창으로 넘어온 'Data'를 보시길 바랍니다</h2>
    <div>
      <h3>POST - /api/v1/user 회원가입</h3>
      <button id="join--submit" v-on:click="makeOneUser" class="mt-0 btn btn-secondary">SIGN UP</button>
    </div>

    <div>
      <h3>POST - /login 로그인</h3>
      <button id="login-btn" v-on:click="login" class="mt-0 btn btn-secondary">login</button>
    </div>

    <div>
      <h3>GET - /api/v1/user 현재 로그인된 사용자 정보 상세조회</h3>
      <button id="get-user-info" v-on:click="findUser" class="mt-0 btn btn-secondary">Get user info test btn</button>
    </div>

    <div>
      <h3>GET - /api/v1/recommendation 문제 1개 추천받기</h3>
      <button id="recommendation" v-on:click="recommendation" class="mt-0 btn btn-secondary">문제 하나 추천</button>
    </div>

    <div>
      <h3>GET - /api/v1/recommendation/reload 문제 1개 다시 추천받기</h3>
      <button id="recommendationAgain" v-on:click="recommendationAgain" class="mt-0 btn btn-secondary">문제 하나 다시 추천</button>
    </div>

    <div>
      <h3>POST - /api/v1/recommendation/additional 그 날 추천된 첫 문제를 풀었기에 추가적인 문제 추천받는 기능</h3>
      <button id="nextProblem" v-on:click="nextProblem" class="mt-0 btn btn-secondary">다음 문제 추천</button>
    </div>

    <div>
      <h3>POST - /api/v1/recommendation 추천받은 문제를 풀었는지 판단해주고, 풀었다면 사용자가 푼 문제 리스트에 해당 문제를 추가하는 API</h3>
      <button id="checkIfSolved" v-on:click="checkIfSolved" class="mt-0 btn btn-secondary">풀었나?</button>
    </div>

    <hr>
    <h1>System API - ADMIN만 사용이 가능</h1>

    <div>
      <h3>POST - /login admin계정으로 로그인</h3>
      <button id="adminLogin" v-on:click="adminLogin" class="mt-0 btn btn-secondary">admin login</button>
    </div>

    <div>
      <h3>POST - /api/v1/system/problem-list DB에 백준 최신 문제 저장하기</h3>
      <button id="migration" v-on:click="updateProblemList" class="mt-0 btn btn-secondary">백준문제 최신사항 반영</button>
    </div>

    <div>
      <h3>GET - /api/v1/system/{userId}/reloadCount [user id] 에 해당하는 사용자의 reloadCount 초기화</h3>
      <button id="resetSpecificUserReloadCount" v-on:click="reloadCountReset" class="mt-0 btn btn-secondary">user 한 명 reload count reset</button>
    </div>

    <div>
      <h3>GET - /api/v1/system/reloadCount 모든 사용자의 reloadCount 초기화</h3>
      <button id="resetAllUsersReloadCount" v-on:click="resetAllUsersReloadCount" class="mt-0 btn btn-secondary">user 전체 reload count reset</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      
    }
  },
  methods: {
    adminLogin() {
      /**
       * system api는 admin 권한을 가진 사용자만이 요청을 할 수 있다. 따라서 일반 사용자는 /api/v1/system/** api를 사용 못한다.
       * 따라서 아래 계정은 server에서 미리 프로그램이 실행될 때 마다 생성되는 관리자 계정이다.
       */
      axios({
        method: 'post',
        url: 'http://localhost:8080/login',
        data: {
          username: 'admin', // admin 계정 id,pw를 내가 이렇게 설정함. setting 도 대충 설정.
          password: '1234' // server/backjoon-recommender/src/main/java/com/khk/backjoonrecommender/service/impl/BasicUserService.java 에 가면 볼 수 있음.
        }
      }).then(function (response) {
        // console.log(response.headers.authorization);
        console.log("admin login success");
        const token = response.headers.authorization;
        localStorage.setItem('Authorization', token);
      });
    },
    resetAllUsersReloadCount() {
      const token = localStorage.getItem('Authorization')
      const headers = {
        'Authorization': token
      };

      axios({
        method: 'PATCH',
        url: `http://localhost:8080/api/v1/system/reloadCount`,
        headers: headers
      }).then(function (response) {
        console.log(response);
      })
    },
    checkIfSolved() {
      const token = localStorage.getItem('Authorization')
      const headers = {
        'Content-type': 'application/json; charset=UTF-8',
        'Authorization': token
      };

      const problemId = 1002; // 1002 번 문제를 풀었는지 확인하기 위함
      // => 이 값은 당연하게도 추천받은 문제 번호를 가져와야 하는 것.

      axios({
        method: 'POST',
        url: 'http://localhost:8080/api/v1/recommendation',
        headers: headers,
        data: problemId
      }).then(function (response) {
        console.log(response);
      })
    },
    nextProblem() {
      const token = localStorage.getItem('Authorization')
      const headers = {
        'Authorization': token
      };

      // 1회용 임시필터를 만약에 사용자가 사용했다면, option="TEMP" 로 줘야 함. 아니라면 option="TODAY"
      const settingRequestDTO = {
          option : "TEMP", // 여기가 무조건 'TEMP' 로 입력되어 요청해야 함
          levels : "1,2,3,4,5,6,7", // 여기에는 사용자가 일회용으로 설정할 난이도 정보
          tags : "math,recursion,geometry,dp", // 여기는 사용자가 일회용으로 설정할 문제유형 정보
          // 아래는 안쓰는 데이터이다.
          sun : "",
          mon : "",
          tue : "",
          wed : "",
          thu : "",
          fri : "",
          sat : ""
      }

      axios({
        method: 'POST',
        url: 'http://localhost:8080/api/v1/recommendation/additional',
        headers: headers,
        data: settingRequestDTO
      }).then(function (response) {
        console.log(response);
      })
    },
    reloadCountReset() {
      const token = localStorage.getItem('Authorization')
      const headers = {
        'Authorization': token
      };

      const userId = 2; // 만약 이 부분에서 오류가 난다면, 해당 로그인된 계정을 H2 DB에서 
      // select * from users 로 찾아서 id가 몇번인지 확인한 후, 존재하는지 확인한다.
      axios({
        method: 'PATCH',
        url: `http://localhost:8080/api/v1/system/${userId}/reloadCount`,
        headers: headers
      }).then(function (response) {
        console.log(response);
      })
    },
    recommendationAgain() {
      const token = localStorage.getItem('Authorization')
      const headers = {
        'Authorization': token
      };

      axios({
        method: 'GET',
        url: 'http://localhost:8080/api/v1/recommendation/reload',
        headers: headers
      }).then(function (response) {
        console.log(response);
      })
    },
    recommendation() {
      const token = localStorage.getItem('Authorization')
      const headers = {
        'Authorization': token
      };

      axios({
        method: 'GET',
        url: 'http://localhost:8080/api/v1/recommendation',
        headers: headers
      }).then(function (response) {
        console.log(response);
      })
    },
    updateProblemList() {
      const token = localStorage.getItem('Authorization')
      const headers = {
        'Authorization': token
      };

      axios({
        method: 'PATCH',
        url: 'http://localhost:8080/api/v1/system/problem-list',
        headers: headers
      }).then(function (response) {
        console.log(response);
      })
    },
    makeOneUser() {
      const userRegisterRequestDto = {
          username: "kjj123", // 회원가입 ID 조건 : 4 ~ 20자 범위
          baekJoonId: "rlawowns000", // 회원가입 PW 조건 : 8 ~ 20자 범위
          password: "12345678",
          option : "TODAY",
          levels : "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15",
          tags : "recursion,dfs,bfs,greedy,math,dp",
          sun : "Math,DFS,BFS",
          mon : "",
          tue : "",
          wed : "Math,DFS,BFS",
          thu : "Math,DFS,BFS",
          fri : "Math,DFS,BFS",
          sat : ""
      };

      console.log(userRegisterRequestDto);

      axios({
        method: 'post',
        url: 'http://localhost:8080/api/v1/user',
        data: userRegisterRequestDto,
      }).then(function (response) {
        console.log(response);
      })
    },
    login() {
      axios({
        method: 'post',
        url: 'http://localhost:8080/login',
        data: {
          username: 'kjj123',
          password: '12345678'
        }
      }).then(function (response) {
        // console.log(response.headers.authorization);
        console.log("login success");
        const token = response.headers.authorization;
        localStorage.setItem('Authorization', token);
      });
    },

    findUser() {
      const userToken = localStorage.getItem('Authorization');
      console.log(userToken);
      const headers = {
        'Content-type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'Authorization': userToken
      };

      axios({
        method: 'get',
        url: 'http://localhost:8080/api/v1/user',
        headers: headers
      }).then(function (response) {
        console.log(response);
      })
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
