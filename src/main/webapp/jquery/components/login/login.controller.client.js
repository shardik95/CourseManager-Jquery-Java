(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn, $userdata;
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
            var text=response.text();
            return text;
        }).then(function(text){
            var len=text.length;
            if(len>0) {
                var json = JSON.parse(text);
                console.log(json);
                $userdata=json.id;
            }
            return len;
        }).then(success);

    }

    function success(length){
        //console.log($userdata);
        if(length>0){
            window.location.href = "../profile/profile.template.client.html?userid="+$userdata;
        }
        else
            alert("cannot login! User not found");
    }

})();
