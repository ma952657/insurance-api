import React from 'react';
import {Link} from "react-router-dom";

const InputForm = ({title, creditcard, errors, onSubmitForm, handleInputChange}) => {

    const {name, number, balance, limit} = creditcard;
    const {nameError, numberError, balanceError, limitError} = errors;

    return (
        <div className="container mt-5">
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center mt-3">{title}</h3>
                    <div className="card-body">
                        <form onSubmit={onSubmitForm}>
                            <div className="form-group">
                                <label>Name</label>
                                <input
                                    type="text"
                                    name="name"
                                    className={nameError ? "form-control is-invalid" : "form-control"}
                                    value={name}
                                    onChange={handleInputChange}/>
                                <div className="invalid-feedback">{nameError}</div>
                            </div>
                            <div className="form-group">
                                <label>Number</label>
                                <input
                                    type="text"
                                    name="number"
                                    className={numberError ? "form-control is-invalid" : "form-control"}
                                    value={number}
                                    onChange={handleInputChange}/>
                                <div className="invalid-feedback">{numberError}</div>
                            </div>
                            <div className="form-group">
                                <label>Limit</label>
                                <input
                                    type="text"
                                    name="limit"
                                    className={limitError ? "form-control is-invalid" : "form-control"}
                                    value={limit}
                                    onChange={handleInputChange}/>
                                <div className="invalid-feedback">{limitError}</div>
                            </div>
                            <div className="form-group">
                                <label>Balance</label>
                                <input
                                    type="decimal"
                                    name="balance"
                                    className={balanceError ? "form-control is-invalid" : "form-control"}
                                    value={balance}
                                    onChange={handleInputChange}/>
                                <div className="invalid-feedback">{balanceError}</div>
                            </div>
                            <input type="submit" className="btn btn-success" value="Save"/>
                            <Link to={"/"}>
                                <button className="btn btn-danger">Cancel</button>
                            </Link>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default InputForm;