import React from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {Provider} from "react-redux";
import store from "../../store";

import CreditCardsListComponent from "../creditcard-components/card-list-component";
import CreditCardAddComponent from "../creditcard-components/creditcard-add-component";
import Header from "../parts/header";
import Footer from "../parts/footer";

import "./app.css"

const App = () => {
    return (
        <Provider store={store}>
            <Router>
                <Header/>
                <div className="container">
                    <Switch>
                        <Route exact path="/" component={CreditCardsListComponent}/>
                        <Route exact path="/creditcards/add" component={CreditCardAddComponent}/>
                    </Switch>
                </div>
                <Footer/>
            </Router>
        </Provider>
    );
};

export default App;