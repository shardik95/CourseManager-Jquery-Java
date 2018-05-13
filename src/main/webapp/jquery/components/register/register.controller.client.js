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

        userService.register(user).then(function (response) {
            return response.text();
        }).then(function(text){
            return text.length;
        }).then(success);

    }

    function success(length){
        if(length>0)
            alert("Registered Successfully!");
        else
            alert("User already exists");
    }


})();
