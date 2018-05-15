(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn, $userData;
    var userService = new UserServiceClient();

    $(main);

    /**
     * DOM on ready function
     */
    function main() {
        $usernameFld=$("#username");
        $passwordFld=$("#password");
        $loginBtn=$("#signin");
        $loginBtn.click(login);

    }

    /**
     * login on click function
     * created json object,calls user service login
     */
    function login() {
        $usernameFld=$("#username").val();
        $passwordFld=$("#password").val();

        var user=new User($usernameFld,$passwordFld,null,null,null,null,null,null);

        userService.login(user).then(function (response) {
            var text=response.text();
            return text;
        }).then(function(text){
            var len=text.length;
            if(len>0) {
                var json = JSON.parse(text);
                console.log(json);
                $userData=json.id;
            }
            return len;
        }).then(success);

    }

    /**
     * success function when user logged in successfully or unsuccessfully
     * @param length - length of the data
     */
    function success(length){
        if(length>0){
            window.location.href = "../profile/profile.template.client.html?userid="+$userData;
        }
        else
            $("#cannotLogin").css("display","block");
    }

})();
