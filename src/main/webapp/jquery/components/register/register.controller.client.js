(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $registerBtn=$("#registerBtn").click(validation);
        $usernameFld=$("#usernameFld");
        $passwordFld=$("#passwordFld");
        $verifyPasswordFld=$("#verifyPasswordFld");
    }

    function validation(){
        $usernameFld=$("#usernameFld").val();
        $passwordFld=$("#passwordFld").val();
        $verifyPasswordFld=$("#verifyPasswordFld").val();

        if($passwordFld!=$verifyPasswordFld){
            $("#matchPassword").css("display","block");
        }
        else{
            register();
        }
    }

    function register() {


        /*var user={
            username:$usernameFld,
            password:$passwordFld,
        };*/

        var user=new User($usernameFld,$passwordFld,null,null,null,null,null,null);

        userService.register(user).then(function (response) {
            return response.text();
        }).then(function(text){
            return text.length;
        }).then(success);

    }

    function success(length){
        if(length>0) {
            $("#matchPassword").css("display","none");
            $("#creation").css("display", "block");
        }
        else{
            $("#matchPassword").css("display","none");
            $("#cannotCreate").css("display","block");
        }

    }


})();
