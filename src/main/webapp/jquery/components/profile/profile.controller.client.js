(function () {

    $(init);

    var userService=new UserServiceClient();
    var $staticEmail;
    var $firstName;
    var $lastName;

    function init() {
        $staticEmail=$("#staticEmail");
        $firstName=$("#firstName");
        $lastName=$("#lastName");
        $update=$("#updateBtn")
            .click(updateUser);
        //findUserById(172);

    };

    function updateUser(){
        var user={
            firstName:$firstName.val(),
            lastName:$lastName.val()
        };

        //userService.updateUser(172,user).then(success);

    }

    function success(){
        alert("success");
    }

    function findUserById(userId){
        userService.findUserById(userId).then(renderUser);
    }

    function renderUser(user){
        $staticEmail.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
    }




})();