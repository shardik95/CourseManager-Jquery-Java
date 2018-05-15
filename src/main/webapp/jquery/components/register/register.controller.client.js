(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    /**
     * DOM on ready function
     */
    function main() {
        $registerBtn=$("#registerBtn").click(validation);
        $usernameFld=$("#usernameFld");
        $passwordFld=$("#passwordFld");
        $verifyPasswordFld=$("#verifyPasswordFld");
    }

    /**
     * Function to validate password
     */
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

    /**
     * Function when sign up button is clicked
     */
    function register() {

        var user=new User($usernameFld,$passwordFld,null,null,null,null,null,null);

        userService.register(user).then(function (response) {
            return response.text();
        }).then(function(text){
            return text.length;
        }).then(success);

    }

    /**
     * Function when user register successfully or unsuccessfully
     * @param length
     */
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
