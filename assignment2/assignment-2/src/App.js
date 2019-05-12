import React from 'react';
import './App.css';
import SmartUsersList from './view/SmartUsersList';
import {Route, Switch, HashRouter} from "react-router-dom"
import SmartQuestionsList from './view/SmartQuestionsList';


const App = () => ( 
  <div className="App">
    <HashRouter>
      <Switch>
        <Route exact="true" component={SmartUsersList} path="/"/>
        <Route exact="true" component={SmartQuestionsList} path="/questions"/>
        
      </Switch>
    </HashRouter>
  </div>
    );
 

export default App;
