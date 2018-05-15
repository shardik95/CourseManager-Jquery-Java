function UserServiceClient(){
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateProfile = updateProfile;
    this.register=register;
    this.login = login;
    this.updateUser=updateUser;
    this.url =
        'http://localhost:8080/api/user';
    this.registerUrl =
        'http://localhost:8080/api/register';
    this.loginUrl='http://localhost:8080/api/login';
    this.profileUrl='http://localhost:8080/api/profile';
    var self = this;

    /**
     * Service to update the User
     * @param userId - userid
     * @param user - User
     * @returns {Promise<Response>}
     */
    function updateUser(userId,user){
        return fetch(self.url+"/"+userId,{
            method:'put',
            body: JSON.stringify(user),
            headers: {
                'content-type':'application/json'
            }
        });
    }

    /**
     * Service to login the user
     * @param user
     * @returns {Promise<Response>}
     */
    function login(user) {
        return fetch(self.loginUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type':'application/json'
            }

        });
    }

    /**
     * Function to register the user
     * @param user
     * @returns {Promise<Response>}
     */
    function register(user){
        return fetch(self.registerUrl, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type':'application/json'
            }

        });
    }

    /**
     * Service to update the profile
     * @param user
     * @returns {Promise<Response>}
     */
    function updateProfile(user){
        return fetch(self.profileUrl ,{
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type':'application/json'
            }
        });

    }

    /**
     * Service to find User by Id
     * @param userId
     * @returns {Promise<any>}
     */
    function findUserById(userId){
        return fetch(self.url +'/'+userId)
            .then(function (response) {
                return response.json();
        });
    }

    /**
     * Service to delete the user
     * @param userId
     * @returns {Promise<Response>}
     */
    function deleteUser(userId){
        return fetch(self.url +'/'+userId,{
            method:'delete'
        })
    }

    /**
     * function to createUser
     * @param user
     * @returns {Promise<Response>}
     */
    function createUser(user){
        return fetch(self.url, {

            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type':'application/json'
            }

        });
    }

    /**
     * Function to find all users
     * @returns {Promise<any>}
     */
    function findAllUsers(){
        return fetch(self.url)
            .then(function (response) {
            return response.json();
        });
    }
}