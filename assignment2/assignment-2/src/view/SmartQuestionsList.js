import React, { Component } from "react";
import userModel from "../model/userModel";
import QuestionsList from "./QuestionsList"
import questionModel from "../model/questionModel"
import questionsListPresenter from "../presenter/questionsListPresenter";

const mapModelStateToComponentState = modelState => ({
    questions : modelState.questions,
    title: modelState.newQuestion.title,
    text: modelState.newQuestion.text,
    tags: modelState.newQuestion.tags
});

const comparator = function(question1, question2)  {
    if (question1.creationDate > question2.creationDate){
        return 1;
    }
    if (question1.creationDate < question2.creationDate){
        return -1;
    }
    return 0;
}

export default class SmartQuestionsList extends Component {
    constructor(){
        super();
        this.state = mapModelStateToComponentState(questionModel.state);
        this.state.questions.sort(comparator);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        questionModel.addListener("change", this.listener);
        questionsListPresenter.onInit();
    }

    componentWillUnmount(){
        userModel.removeListener("change", this.listener);
    }

    render(){
        return (
            <QuestionsList
            onCreate={questionsListPresenter.onCreate}
            onChange={questionsListPresenter.onChange}
            onSearchTag={questionsListPresenter.onSearchTag}
            onSearchTitle={questionsListPresenter.onSearchTitle}
            newQuestionTitle = {this.state.title}
            newQuestionText = {this.state.text}
            newQuestionTags = {this.state.tags}
            questions = {this.state.questions} />
        );
    }
} 