function UserServiceClient(){
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    /*this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;*/
    this.url =
        'http://localhost:8080/api/user';
    var self = this;

    function createUser(user){
        return fetch(self.url, {

            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type':'application/json'
            }

        });
    }

    function findAllUsers(){
        return fetch(self.url)
            .then(function (response) {
            return response.json();
        });
    }
}