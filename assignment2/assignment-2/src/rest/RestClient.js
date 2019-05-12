

const BASE_URL = "http://localhost:8080";

export default class RestClient{
    constructor(username,password){
        console.log(username + ";");
        console.log(password+ ";");
        this.authorization = "Basic " + btoa(username + ":" + password);
    }

    loadAllQuestions(){
        return fetch(BASE_URL + "/questions", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            console.log(response);
            return response.json();}
        );
    }

    addQuestion(body){
        console.log("Sending request with authorization:" + this.authorization);
        return fetch(BASE_URL + "/questions",{
            method: "POST",
            body: JSON.stringify(body),
            headers:{
                "Authorization": this.authorization,
                'Content-Type' : 'application/json'
            }
        });
        
    }

    filterTitle(title){
        return fetch(BASE_URL + "/questions/title/" + title,{
            method: "GET",
            headers:{
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    filterTag(tag){
        return fetch(BASE_URL + "/questions/tag/" + tag,{
            method: "GET",
            headers:{
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }
}