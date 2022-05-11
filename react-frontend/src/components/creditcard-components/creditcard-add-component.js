import React, {Component} from 'react';
import {connect} from "react-redux";
import PropTypes from "prop-types";

import {addNewCard} from "../../actions/creditcard-actions";
import InputForm from "../parts/input-form";

class CreditCardAddComponent extends Component {
    state = {
        name: "",
        number: "",
        balance: 0.00,
        limit: "",
        errors: {}
    };
    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    handleInputChange = (event) => {
        const {name, value} = event.target;

        this.setState({
            [name]: value
        });
    };

    addNewCard = (event) => {
        event.preventDefault();

        const {name, number, balance, limit} = this.state;
        const creditcard = {name, number, balance, limit};

        this.props.addNewCard(creditcard, this.props.history);
    };

    render() {
        return (
            <div>
                <InputForm
                    title={"Add New Card"}
                    creditcard={this.state}
                    errors={this.state.errors}
                    onSubmitForm={this.addNewCard}
                    handleInputChange={this.handleInputChange}/>
            </div>
        );
    }
}

CreditCardAddComponent.propTypes = {
    addNewCard: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    errors: state.errors,
});

export default connect(mapStateToProps, {addNewCard})(CreditCardAddComponent);