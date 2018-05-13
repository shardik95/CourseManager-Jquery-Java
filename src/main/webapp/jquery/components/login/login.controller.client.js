(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld=$("#username");
        $passwordFld=$("#password");
        $loginBtn=$("#signin");

        $loginBtn.click(login);

    }
    function login() {
        $usernameFld=$("#username").val();
        $passwordFld=$("#password").val();

        var user ={
            username:$usernameFld,
            password:$passwordFld
        };

        userService.login(user).then(function (response) {
            return response.text();
        }).then(function(text){
            return text.length;
        }).then(success);

    }

    function success(length){
        if(length>0)
            window.location.href = "../profile/profile.template.client.html";
        else
            alert("cannot login! User not found");
    }

})();
