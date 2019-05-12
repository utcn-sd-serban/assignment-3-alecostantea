 import {EventEmitter} from "events";

 class UserModel extends EventEmitter {
     constructor() {
         super();
         this.state = {
             users: [{
                 Username: "alexpopa09",
                 Password: "dovlecei"
             },{
                 Username: "maria97",
                 Password: "imiplacaricii"
             
             }],
             newUser: {
                Username: "",
                Password: ""
             }
            };
         }

         addUser(username, password){
            this.state = {
                ...this.state,
                users: this.state.users.concat([{
                    Username: username,
                    Password: password
                }])
            };
            this.emit("change", this.state);
         }

         changeNewUserProperty(property, value){
            this.state = {
                ...this.state,
                newUser: {
                    ...this.state.newUser,
                    [property]: value
                }
            };
            this.emit("change", this.state);
         }
         }
     
 

 const userModel = new UserModel();

 export default userModel;