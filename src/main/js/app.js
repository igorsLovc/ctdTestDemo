'use strict';

const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
const client = require('./client'); // <3>
const when = require('when');
const follow = require('./follow'); // function to hop multiple links by "rel"

const root = '/api';

class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = {bank:{}, paymentInfoFields: []};
		this.onCreate = this.onCreate.bind(this);
	}

	componentDidMount() { // <2>
		client({method: 'GET', path: '/api/banks/1'}).done(response => {
			this.setState({bank: response.entity});
		});
		client({method: 'GET', path: '/api/paymentInfoFields'}).done(response => {
			this.setState({paymentInfoFields: response.entity._embedded.paymentInfoFields});
		});
	
	}

	
	onCreate(newPaymentInfoFields) {
		follow(client, root, ['paymentInfoFields'])
		.then(response => {
			return client({
				method: 'POST',
				path:'/paymentProcess', // response.entity._links.self.href,
				entity: newPaymentInfoFields,
				headers: {'Content-Type': 'application/json'}
			})
		}).then(_success, _error);
		function _success() {
	        alert("Successfully done");
	    }

	    function _error() {
	        alert("Something went wrong");
	    }
		
	}
	

	render() { // <3>
		return (
				<div>
			<Bank bank={this.state.bank}/>
			<PaymentFields paymentInfoFields={this.state.paymentInfoFields} onCreate={this.onCreate}/>
			</div>
		)
	}
}

class Bank extends React.Component{
	render() {
		return (
		<table>
		<tbody>
			<tr>
				<td>{this.props.bank.name}</td>
			</tr>
			<tr>
				<td>{this.props.bank.details}</td>
			</tr>
			<tr>
			<td>{this.props.bank.address}</td>
		</tr>
		</tbody>
		</table>
		)
	}
}

class PaymentFields extends React.Component{
	
	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}
	
	handleSubmit(e) {
		e.preventDefault();
		const newPaymentInfoFields = [];
		this.props.paymentInfoFields.forEach(paymentInfoField => {
			paymentInfoField.value = ReactDOM.findDOMNode(this.refs[paymentInfoField.field]).value.trim();
			newPaymentInfoFields.push(paymentInfoField);
		});
		this.props.onCreate(newPaymentInfoFields);
		
	}
	
	render() {
		const inputsPayment = this.props.paymentInfoFields.map(paymentInfoField =>
			<p key={paymentInfoField.field}>{paymentInfoField.fieldName}:
				<input required type="text" placeholder={paymentInfoField.fieldName} ref={paymentInfoField.field} className="field"/>
			</p>
		);
		return (
			<div>
				<h2>Create new payment</h2>

				<form>
					{inputsPayment}
					<button onClick={this.handleSubmit}>Create payment and send by mail</button>
				</form>
			</div>
		)
	}
}



ReactDOM.render(
	<App />,
	document.getElementById('react')
)

