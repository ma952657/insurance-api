import {combineReducers} from "redux";
import errorsReducer from "./error-reducer";
import creditcardReducer from "./creditcard-reducer";

export default combineReducers({
    errors: errorsReducer,
    creditcards: creditcardReducer
});