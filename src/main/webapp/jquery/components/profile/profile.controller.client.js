(function () {

    var $usernameFld,$phoneFld,$emailFld,$roleFld,$dateOfBirthFld;
    var id, $logoutbtn;

    $(init);


    var userService=new UserServiceClient();

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

    function updateProfile(){
        $usernameFld=$("#usernameFld").val();
        $phoneFld=$("#phoneFld").val();
        $emailFld=$("#emailFld").val();
        $roleFld=$("#roleFld").val();
        $dateOfBirthFld= $("#dateOfBirthFld").val();

        var user={
            username:$usernameFld,
            phone:$phoneFld,
            email:$emailFld,
            role:$roleFld,
            dateOfBirth:$dateOfBirthFld
        };

        //console.log(user.username);
        userService.updateProfile(user).then(function (response) {
            return response.text();
        }).then(function(text){
            return text.length;
        }).then(success);
    }

    function renderUser(user){
        $("#usernameFld").val(user.username);
        $("#phoneFld").val(user.phone);
        $("#emailFld").val(user.email);
        $("#roleFld").val(user.role);
        $("#dateOfBirthFld").val(user.dateOfBirth);
    }

    function success(length){
        if(length>0)
            alert("Profile Updated Successfully!");
        else
            alert("Profile cannot be Updated!");
    }

    function logout() {
        alert("logged out successfully!");
        window.location.href="../login/login.template.client.html";
    }



})();