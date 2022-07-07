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
      <h3>POST - /api/v1/system/problem-list DB에 백준 최신 문제 저장하기</h3>
      <button id="migration" v-on:click="updateProblemList" class="mt-0 btn btn-secondary">백준문제 최신사항 반영</button>
    </div>

    <div>
      <h3>GET - /api/v1/recommendation 문제 1개 추천받기</h3>
      <button id="recommendation" v-on:click="recommendation" class="mt-0 btn btn-secondary">문제 하나 추천</button>
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
      const userRequestDTO = {
          username: "kjj",
          baekJoonId: "rlawowns000",
          password: "123"
      };

      const settingRequestDTO = {
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

      const signUpRequestDTO = {
          userRequestDTO : userRequestDTO,
          settingRequestDTO : settingRequestDTO
      };

      console.log(signUpRequestDTO);

      axios({
        method: 'post',
        url: 'http://localhost:8080/api/v1/user',
        data: signUpRequestDTO,
      }).then(function (response) {
        console.log(response);
      })
    },
    login() {
      axios({
        method: 'post',
        url: 'http://localhost:8080/login',
        data: {
          username: 'kjj',
          password: '123'
        }
      }).then(function (response) {
        // console.log(response.headers.authorization);
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
