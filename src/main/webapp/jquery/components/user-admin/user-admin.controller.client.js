(function (){

    jQuery(main);

    var template;
    var tbody;
    var userService=new UserServiceClient();

    function main(){
        jQuery("h1#title").css('color','red').html('User-Administration');

        template=$("#template");
        tbody=$("tbody");

        $("#createUser").click(createUser);

        findAllUsers();

    }

    function findAllUsers(){
        userService.findAllUsers().then(renderUsers);
    }

    function createUser(){
        var username = $("#UsernameFld").val();
        var password = $("#PasswordFld").val();
        var firstname =$("#FirstNameFld").val();
        var lastname= $("#LastNameFld").val();

        var user={
          username: username,
          password: password,
          firstName: firstname,
          lastName: lastname
        };

        userService.createUser(user).then(findAllUsers);

    }

    function renderUsers(users){
        for(i=0;i<users.length;i++){
            var user=users[i];
            var tr1=template.clone();
            tr1.find(".username").html(user.username);
            tbody.append(tr1);
        }
    }


})();