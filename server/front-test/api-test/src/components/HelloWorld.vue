<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p>
    <h3>Installed CLI Plugins</h3>
    <ul>
      <li><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-babel" target="_blank" rel="noopener">babel</a></li>
      <li><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-eslint" target="_blank" rel="noopener">eslint</a></li>
    </ul>
    <h3>Essential Links</h3>
    <ul>
      <li><a href="https://vuejs.org" target="_blank" rel="noopener">Core Docs</a></li>
      <li><a href="https://forum.vuejs.org" target="_blank" rel="noopener">Forum</a></li>
      <li><a href="https://chat.vuejs.org" target="_blank" rel="noopener">Community Chat</a></li>
      <li><a href="https://twitter.com/vuejs" target="_blank" rel="noopener">Twitter</a></li>
      <li><a href="https://news.vuejs.org" target="_blank" rel="noopener">News</a></li>
    </ul>
    <h3>Ecosystem</h3>
    <ul>
      <li><a href="https://router.vuejs.org" target="_blank" rel="noopener">vue-router</a></li>
      <li><a href="https://vuex.vuejs.org" target="_blank" rel="noopener">vuex</a></li>
      <li><a href="https://github.com/vuejs/vue-devtools#vue-devtools" target="_blank" rel="noopener">vue-devtools</a></li>
      <li><a href="https://vue-loader.vuejs.org" target="_blank" rel="noopener">vue-loader</a></li>
      <li><a href="https://github.com/vuejs/awesome-vue" target="_blank" rel="noopener">awesome-vue</a></li>
    </ul>

    <button id="join--submit" v-on:click="makeOneUser" class="mt-0 btn btn-secondary">SIGN UP - 현재 버그 있어서 html 파일에서 직접 해주세요</button>
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
        data: JSON.stringify(signUpRequestDTO),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json'
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
