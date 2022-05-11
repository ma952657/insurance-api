import axios from "axios";
import {GET_ERRORS, GET_ALL_CC_DETAILS} from "./types";

const CC_API_BASE_URL = "http://localhost:8082/creditcard";

export const addNewCard = (creditcard, history) => async dispatch => {
    try {
        await axios.post(CC_API_BASE_URL + "/addNewCard", creditcard);
        history.push("/");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        })
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
};

export const getCreditCards = () => async dispatch => {
    const response = await axios.get(CC_API_BASE_URL + "/getCards");
    dispatch({
        type: GET_ALL_CC_DETAILS,
        payload: response.data
    })
};
