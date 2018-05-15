(function (){

    /**
     * Function when DOM is ready
     */
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

    /**
     * Function to find user by id
     * @param userId - user id of the function
     * @returns {*} promise
     */
    function findUserById(userId){
        return userService.findUserById(userId);
    }

    /**
     * function to update the user
     */
    function updateUser(){
        $usernameFld = $("#usernameFld").val();
        $firstNameFld =$("#firstNameFld").val();
        $lastNameFld= $("#lastNameFld").val();
        $userRole=$("#roleFld").val();

        var user=new User($usernameFld,null,null,$firstNameFld,$lastNameFld,null,$userRole,null);

        var userId=$tbodyform.attr("id");
        userService.updateUser(userId,user).then(clearform).then(findAllUsers);
        $tbodyform.removeAttr("id");

    }

    /**
     * Function to clear the form
     */
    function clearform(){
        $usernameFld = $("#usernameFld").val("");
        $passwordFld = $("#passwordFld").val("");
        $firstNameFld =$("#firstNameFld").val("");
        $lastNameFld= $("#lastNameFld").val("");
    }

    /**
     * Function to find all users
     */
    function findAllUsers(){
        userService.findAllUsers().then(renderUsers);
    }

    /**
     * Function to create a user
     */
    function createUser(){
        $usernameFld = $("#usernameFld").val();
        $passwordFld = $("#passwordFld").val();
        $firstNameFld =$("#firstNameFld").val();
        $lastNameFld= $("#lastNameFld").val();
        $userRole=$("#roleFld").val();

        var user=new User($usernameFld,$passwordFld,null,$firstNameFld,$lastNameFld,null,$userRole,null);

        userService.createUser(user).then(findAllUsers);

    }

    /**
     * Function to render all users on page
     * @param users - list of users
     */
    function renderUsers(users){
        $tbody.empty();
        for(i=0;i<users.length;i++){
            var user=users[i];
            var clone=$userRowTemplate.clone();
            clone.attr('id',user.id);
            clone.find(".remove").click(deleteUser);
            clone.find(".edit").click(editUser);
            clone.find(".wbdv-username").html(user.username );
            clone.find(".wbdv-first-name").html(user.firstName);
            clone.find(".wbdv-last-name").html(user.lastName);
            clone.find(".wbdv-role").html(user.role);
            $tbody.append(clone);
        }
    }

    /**
     * FUnction to delete the user
     * @param event - event on button
     */
    function deleteUser(event){
        $removeBtn =$(event.currentTarget);
        var userId =$removeBtn.parent().parent().parent().attr("id");
        userService.deleteUser(userId)
            .then(findAllUsers);
    }

    /**
     * Function when edit user is clicked
     * @param event -  event on the button
     */
    function editUser(event){
        $editBtn=$(event.currentTarget);
        var userId =$editBtn.parent().parent().parent().attr("id");
        $tbodyform.attr("id",userId);
        findUserById(userId).then(renderUser);

    }

    /**
     * Function to render single user,populate the form
     * @param user
     */
    function renderUser(user){
        $tbodyform.find("#usernameFld").val(user.username);
        $tbodyform.find("#firstNameFld").val(user.firstName);
        $tbodyform.find("#lastNameFld").val(user.lastName);
        $tbodyform.find("#roleFld").val(user.role);
    }


})();
