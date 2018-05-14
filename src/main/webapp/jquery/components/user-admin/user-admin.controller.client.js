(function (){

    jQuery(main);

    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn, $updateBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var $userRole,$tbodyform;
    var userService=new UserServiceClient();

    function main(){

        $userRowTemplate=$(".wbdv-template");
        $tbody=$(".wbdv-tbody");
        $tbodyform=$(".wbdv-form");
        $createBtn= $(".create");
        $createBtn.click(createUser);
        $updateBtn=$(".update");
        $updateBtn.click(updateUser);
        findAllUsers();

    }

    function findUserById(userId){
        return userService.findUserById(userId);
    }

    function updateUser(){

        $usernameFld = $("#usernameFld").val();
        $firstNameFld =$("#firstNameFld").val();
        $lastNameFld= $("#lastNameFld").val();
        $userRole=$("#roleFld").val();

        var user={
            username:$usernameFld,
            firstName:$firstNameFld,
            lastName:$lastNameFld,
            role:$userRole
        };

        var userId=$tbodyform.attr("id");
        userService.updateProfile(userId,user).then(clearform).then(findAllUsers);
        $tbodyform.removeAttr("id");

    }

    function clearform(){
        $usernameFld = $("#usernameFld").val("");
        $passwordFld = $("#passwordFld").val("");
        $firstNameFld =$("#firstNameFld").val("");
        $lastNameFld= $("#lastNameFld").val("");
    }

    function findAllUsers(){
        userService.findAllUsers().then(renderUsers);
    }

    function createUser(){

        $usernameFld = $("#usernameFld").val();
        $passwordFld = $("#passwordFld").val();
        $firstNameFld =$("#firstNameFld").val();
        $lastNameFld= $("#lastNameFld").val();
        $userRole=$("#roleFld").val();

        var user={
          username: $usernameFld,
          password: $passwordFld,
          firstName: $firstNameFld,
          lastName: $lastNameFld,
            role:$userRole
        };

        userService.createUser(user).then(findAllUsers);

    }


    function renderUsers(users){
        $tbody.empty();
        for(i=0;i<users.length;i++){
            var user=users[i];
            var clone=$userRowTemplate.clone();
            clone.attr('id',user.id);
            clone.find(".remove").click(deleteUser);
            clone.find(".edit").click(editUser);

            clone.find(".wbdv-username").html(user.username);
            clone.find(".wbdv-first-name").html(user.firstName);
            clone.find(".wbdv-last-name").html(user.lastName);
            clone.find(".wbdv-role").html(user.role);
            $tbody.append(clone);
        }
    }

    function deleteUser(event){

        $removeBtn =$(event.currentTarget);
        var userId =$removeBtn.parent().parent().parent().attr("id");
        userService.deleteUser(userId)
            .then(findAllUsers);
    }


    function editUser(event){
        $editBtn=$(event.currentTarget);
        var userId =$editBtn.parent().parent().parent().attr("id");
        $tbodyform.attr("id",userId);
        findUserById(userId).then(renderUser);

    }

    function renderUser(user){
        $tbodyform.find("#usernameFld").val(user.username);
        $tbodyform.find("#firstNameFld").val(user.firstName);
        $tbodyform.find("#lastNameFld").val(user.lastName);
        $tbodyform.find("#roleFld").val(user.role);
    }


})();
