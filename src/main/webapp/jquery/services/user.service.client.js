function UserServiceClient(){
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateProfile = updateProfile;
    this.register=register;
    this.login = login;
    this.url =
        'http://localhost:8080/api/user';
    this.registerUrl =
        'http://localhost:8080/api/register';
    this.loginUrl='http://localhost:8080/api/login';
    this.profileUrl='http://localhost:8080/api/profile';
    var self = this;


    function login(user) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type':'application/json'
            }

        });
    }


    function register(user){
        return fetch(self.registerUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type':'application/json'
            }

        });
    }


    function updateProfile(user){
        return fetch(self.profileUrl ,{
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type':'application/json'
            }
        });

    }

    function findUserById(userId){
        return fetch(self.url +'/'+userId)
            .then(function (response) {
                return response.json();
        });
    }

    function deleteUser(userId){
        return fetch(self.url +'/'+userId,{
            method:'delete'
        })
    }

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