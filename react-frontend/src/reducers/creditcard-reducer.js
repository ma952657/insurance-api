import {GET_ALL_CC_DETAILS} from "../actions/types";

const initialState = {
    creditcards: [],
    creditcard: {}
};

export default function (state = initialState, action) {
    const {type, payload} = action;

    switch (type) {
        case GET_ALL_CC_DETAILS:
            return {...state, creditcards: payload};

        default:
            return state;
    }
}
