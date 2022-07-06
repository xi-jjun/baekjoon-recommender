$(function() {
    $("#join--submit").on("click", function() {
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

        // let data = {
        //     name: $('#name').val(),
        //     account: $('#account').val(),
        //     password: $('#password').val(),
        //     age: $('#age').val(),
        //     roles: $('#roles').val(),
        // }

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/v1/user',
            data: JSON.stringify(signUpRequestDTO),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json'
        }).done(function(r) {
            alert("Sign up success");
            console.log(r);
        }).fail(function(r) {
            alert('서버 오류');
        });
    });
});