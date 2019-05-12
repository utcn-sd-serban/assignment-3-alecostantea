import React from "react";

const UsersList = ({users, title, newUserUsername, newUserPassword,
     onCreate, onChange, onSignin}) => (
    <div>
        <h2 className="title">{ title || "Users"}</h2>
        <div>
            <label className="label">Username: </label>
            <input value = {newUserUsername} onChange={ e => onChange("Username", e.target.value ) }/>
            <br />
            <label className="label">Password: </label>
            <input value = {newUserPassword} onChange={ e => onChange("Password", e.target.value ) }/>
            <br />
            <button className="button" onClick={onCreate}>Create</button>
            <button className="button" onClick={onSignin}>Sign in</button>
    </div>
        <hr />
        <table className="table is-striped is-hoverable">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                </tr>
            </thead>
            <tbody>
                {
                    users.map((user,index) => (
                        <tr key = {index}>
                        <td>{user.Username}</td>
                        <td>{user.Password}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    </div>
);

export default UsersList;