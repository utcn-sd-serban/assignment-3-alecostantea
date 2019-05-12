import userModel from "../model/userModel";

class UsersListPresenter {

    onCreate(){
        userModel.addUser(userModel.state.newUser.Username,userModel.state.newUser.Password);
        userModel.changeNewUserProperty("Username","");
        userModel.changeNewUserProperty("Password","");
    }

    onChange(property, value){
        userModel.changeNewUserProperty(property,value);
    }

    onSignin(){   
                window.location.assign("#/questions");
            
        
    }
    
}

const usersListPresenter = new UsersListPresenter();

export default usersListPresenter;