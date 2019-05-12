import React, { Component } from "react";
import userModel from "../model/userModel";
import UsersList from "./UsersList";
import usersListPresenter from "../presenter/usersListPresenter";

const mapModelStateToComponentState = modelState => ({
    users : modelState.users,
    Username: modelState.newUser.newUserUsername,
    Password: modelState.newUser.newUserPassword
});

export default class SmartUsersList extends Component {
    constructor(){
        super();
        this.state = mapModelStateToComponentState(userModel.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        userModel.addListener("change", this.listener);

    }

    componentWillUnmount(){
        userModel.removeListener("change", this.listener);
    }

    render(){
        return (
            <UsersList
            onCreate={usersListPresenter.onCreate}
            onChange={usersListPresenter.onChange}
            onSignin={usersListPresenter.onSignin}
            Username = {this.state.newUserUsername}
            Password = {this.state.newUserPassword}
            users = {this.state.users} />
        );
    }
} 