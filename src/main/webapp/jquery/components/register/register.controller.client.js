(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $registerBtn=$("#registerBtn").click(register);
        $usernameFld=$("#usernameFld");
        $passwordFld=$("#passwordFld");
        $verifyPasswordFld=$("#verifyPasswordFld");
    }
    function register() {
        $usernameFld=$("#usernameFld").val();
        $passwordFld=$("#passwordFld").val();
        $verifyPasswordFld=$("#verifyPasswordFld").val();

        var user={
            username:$usernameFld,
            password:$passwordFld,
        };

        userService.register(user).then(function(response){
            if(response===null){
                alert("user already exists");
            }
            else
                alert("Success!");
        })

    }


})();
