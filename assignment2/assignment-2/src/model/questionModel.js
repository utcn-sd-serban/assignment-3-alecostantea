import {EventEmitter} from "events";
import RestClient from "../rest/RestClient";
import userModel from "./userModel";


 class QuestionModel extends EventEmitter {
     constructor() {
         super();
         this.state = {
             questions: [{
                title: "titlu frumos",
                text: "Cum fac asta?",
                author: "alexpopa09",
                tags: "cool tags are for cool kids",
                creationDate: Date("2019-09-16")
             },{
                 title: "lalala",
                 text: "Ajutor",
                 author: "maria97",
                 tags:"tags cool ceva",
                 creationDate: Date("2018-10-2")
             
             }],
             newQuestion: {
                title: "",
                text: "",
                tags: ""
             }
            };
         }

         loadQuestions(){               
                const client = new RestClient(userModel.state.newUser.Username, userModel.state.newUser.Password);
                return client.loadAllQuestions().then(questions => {
                    this.state = {
                        ...this.state,
                        questions: questions
                    };
                    this.emit("change", this.state);
                })
         }

         addQuestion(title, text, author, tags){
            const client = new RestClient(userModel.state.newUser.Username, userModel.state.newUser.Password);
            const question = {
                id:0,
                title: title,
                text: text,
                author: author,
                tags: tags
            } ;
            console.log(JSON.stringify(question));
            return client.addQuestion(question).then(() => {this.emit("change",this.state);});
            
         }

         filterTitle(title){
            const client = new RestClient(userModel.state.newUser.Username, userModel.state.newUser.Password);
            return client.filterTitle(title).then(questions => {
                this.state = {
                    ...this.state,
                    questions: questions
                };
                this.emit("change", this.state);
            })
            
         }
         filterTag(tag){
            const client = new RestClient(userModel.state.newUser.Username, userModel.state.newUser.Password);
            return client.filterTag(tag).then(questions => {
                this.state = {
                    ...this.state,
                    questions: questions
                };
                this.emit("change", this.state);
            })
           
         }

         changeNewQuestionProperty(property, value){
            this.state = {
                ...this.state,
                newQuestion: {
                    ...this.state.newQuestion,
                    [property]: value
                }
            };
            this.emit("change", this.state);
         }
         }
     
 

 const questionModel = new QuestionModel();

 export default questionModel;