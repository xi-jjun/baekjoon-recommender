<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    
    <h1>API TEST</h1>

    <button id="join--submit" v-on:click="makeOneUser" class="mt-0 btn btn-secondary">SIGN UP</button>
    <button id="login-btn" v-on:click="login" class="mt-0 btn btn-secondary">login</button>
    <button id="get-user-info" v-on:click="findUser" class="mt-0 btn btn-secondary">Get user info test btn</button>
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
    makeOneUser() {
      const userRequestDTO = {
          username: "kjj",
          baekJoonId: "rlawowns000",
          password: "123"
      };

      const settingRequestDTO = {
          option : "TODAY",
          levels : "1,2,3,4,5",
          tags : "Math,DFS,BFS",
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
