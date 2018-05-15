(function () {

    var $usernameFld,$phoneFld,$emailFld,$roleFld,$dateOfBirthFld;
    var id, $firstNameFld,$lastNameFld;

    $(init);

    var userService=new UserServiceClient();

    /**
     * DOM on-ready function
     */
    function init() {
        var query=window.location.search.substring(1);
        var pos = query.indexOf('=');
        if (pos > 0) {
            id = query.substring(pos + 1);
            userService.findUserById(id).then(renderUser);
        }
        $("#updateBtn").click(updateProfile);
        $("#LogoutBtn").click(logout);

    };

    /**
     * function when update profile is clicked
     * created JSON object and calls service updateProfile
     */
    function updateProfile(){
        $usernameFld=$("#usernameFld").val();
        $firstNameFld=$("#firstNameFld").val();
        $lastNameFld=$("#lastNameFld").val();
        $phoneFld=$("#phoneFld").val();
        $emailFld=$("#emailFld").val();
        $roleFld=$("#roleFld").val();
        $dateOfBirthFld= $("#dateOfBirthFld").val();

        var user=new User($usernameFld,null,$emailFld,$firstNameFld,$lastNameFld,$phoneFld,$roleFld,$dateOfBirthFld);

        userService.updateProfile(user).then(function (response) {
            return response.text();
        }).then(function(text){
            return text.length;
        }).then(success);
    }

    /**
     * Function to populate the form with the user
     * @param user
     */
    function renderUser(user){
        $("#usernameFld").val(user.username);
        $("#firstNameFld").val(user.firstName);
        $("#lastNameFld").val(user.lastName);
        $("#phoneFld").val(user.phone);
        $("#emailFld").val(user.email);
        $("#roleFld").val(user.role);

        var dob=user.dateOfBirth;
        if(dob!=null) {
            var position = dob.indexOf('T');
            if (position > 0) {
                var time = dob.substring(0, position);
                $("#dateOfBirthFld").val(time);
            }
        }
    }

    /**
     * success function for successful profile update or unsuccessful
     * @param length - length of the response from server
     */
    function success(length){
        if(length>0)
            $("#saved").css("display","block");
        else
            $("#cannotSave").css("display","block");
    }

    /**
     * Function for logout, navigates to sign in page.
     */
    function logout() {
        $("#Logged").css("display","block");
        window.location.href="../login/login.template.client.html";
    }



})();