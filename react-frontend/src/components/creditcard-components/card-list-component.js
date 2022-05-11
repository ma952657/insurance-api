import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {connect} from "react-redux";
import PropTypes from "prop-types";

import {getCreditCards} from "../../actions/creditcard-actions";

import "./creditcard.css";

class CreditCardsListComponent extends Component {

    componentDidMount() {
        this.props.getCreditCards();
    };

    render() {
        const {creditcards} = this.props.creditcards;

        return (
            <div>
                <h2 className="text-center mt-4">Credit Cards List</h2>
                <div className="row">
                    <Link to={"/creditcards/add"}>
                        <button style={{marginBottom: "10px"}}
                                className="btn btn-success">Add New Card
                        </button>
                    </Link>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead className="bg-dark" style={{color: "white"}}>
                        <tr className="notbold">
                            <th>Name</th>
                            <th>Number</th>
                            <th>Balance</th>
                            <th>Limit</th>
                        </tr>
                        </thead>
                        <tbody>
                        {creditcards.map((creditcard) =>
                            <tr key={creditcard.number}>
                                <td> {creditcard.name} </td>
                                <td> {creditcard.number}</td>
                                <td> {creditcard.balance}</td>
                                <td> {creditcard.limit}</td>
                            </tr>
                        )}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

CreditCardsListComponent.propTypes = {
    getCreditCards: PropTypes.func.isRequired,
    creditcards: PropTypes.object.isRequired
};

const mapStateToProps = (state) => ({
    creditcards: state.creditcards
});

export default connect(mapStateToProps, {getCreditCards})(CreditCardsListComponent);