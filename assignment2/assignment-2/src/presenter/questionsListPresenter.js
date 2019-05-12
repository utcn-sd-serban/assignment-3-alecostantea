import userModel from "../model/userModel";
import questionModel from "../model/questionModel";

class QuestionsListPresenter {

    onCreate(){
        questionModel.addQuestion(questionModel.state.newQuestion.title, questionModel.state.newQuestion.text,userModel.state.newUser.Username, questionModel.state.newQuestion.tags)
        questionModel.changeNewQuestionProperty("title","");
        questionModel.changeNewQuestionProperty("text","");
        questionModel.changeNewQuestionProperty("tags","");
    }

    onChange(property, value){
        questionModel.changeNewQuestionProperty(property,value);
    }

    onSearchTitle(){
        questionModel.filterTitle(questionModel.state.newQuestion.title);
        // let questions = questionModel.state.questions.slice();
        // questionModel.state.questions= [];
        // for(let index = 0 ; index < questions.length; index++) {
        //     if(questions[index].title.includes(questionModel.state.newQuestion.title)) {
        //         questionModel.addQuestion(questions[index].title, questions[index].text,questions[index].author, questions[index].tags)
        //     }
        // }
    }

    onSearchTag(){
        questionModel.filterTag(questionModel.state.newQuestion.tags);
        // let questions = questionModel.state.questions.slice();
        // questionModel.state.questions= [];
        // for(let index = 0 ; index < questions.length; index++) {
        //     if(questions[index].tags.split(" ").includes(questionModel.state.newQuestion.tags)) {
        //         questionModel.addQuestion(questions[index].title, questions[index].text,questions[index].author, questions[index].tags)
        //     }
        // }
    }

    onInit(){
        questionModel.loadQuestions();
    }


}

const questionsListPresenter = new QuestionsListPresenter();

export default questionsListPresenter;