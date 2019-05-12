import React from "react";

const QuestionsList = ({questions, title, newQuestionTitle, newQuestionText, newQuestionTags,
     onCreate, onChange, onSearchTag, onSearchTitle}) => (
    <div>
        <h2 className="title">{ title || "Questions"}</h2>
        <div>
            <label className="label">Title: </label>
            <input value = {newQuestionTitle} onChange={ e => onChange("title", e.target.value ) }/>
            <br />
            <label className="label">Text: </label>
            <input value = {newQuestionText} onChange={ e => onChange("text", e.target.value ) }/>
            <br />
            <label className="label">Tags: </label>
            <input value = {newQuestionTags} onChange={ e => onChange("tags", e.target.value ) }/>
            <br />
            <button className="button is-light is-default" onClick={onCreate}>Create</button>
            <button className="button is-light is-default" onClick={onSearchTitle}>Search Title</button>
            <button className="button is-light is-default" onClick={onSearchTag}>Search tag</button>
            
    </div>
        <hr />
        <table className="table is-striped is-hoverable">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Text</th>
                    <th>author</th>
                </tr>
            </thead>
            <tbody>
                {
                    questions.map((question,index) => (
                        <tr key = {index}>
                        <td>{question.title}</td>
                        <td>{question.text}</td>
                        <td>{question.author}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    </div>
);

export default QuestionsList;