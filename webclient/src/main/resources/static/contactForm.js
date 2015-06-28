var FormComp = React.createClass({displayName: "FormComp",

  // To get rid of those input refs I'm moving those values
  // and the form message into the state
  getInitialState: function() {
    return {
      name: '',
      email: '',
      message: ''
    };
  },

  handleSubmit: function(e) {

    e.preventDefault();

    var userName = this.state.name.trim();
    var userEmail = this.state.email.trim();

    if(!userName || !userEmail) return;

    this.setState({
      name: '',
      email: '',
      message: 'Please wait...'
    });

    // I'm adding a callback to the form submit handler, so you can
    // keep all the state changes in the component.
    this.props.onFormSubmit({
      userName: userName, 
      userEmail: userEmail, 
      url: "/api/submit"
    }, function(data) {
      this.setState({ message: data });
    });
  },

  changeName: function(e) {
    this.setState({
      name: e.target.value
    });
  },

  changeEmail: function(e) {
    this.setState({
      email: e.target.value
    });
  },

  render: function() {
    // the message and the input values are all component state now
    return (
      React.createElement("div", null, 
        React.createElement("div", {className: "result"},  this.state.message), 
        React.createElement("form", {className: "formElem", onSubmit:  this.handleSubmit}, 
          "Name: ", React.createElement("input", {type: "text", className: "userName", name: "userName", value:  this.state.name, onChange:  this.changeName}), React.createElement("br", null), 
          "Email: ", React.createElement("input", {type: "text", className: "userEmail", name: "userEmail", value:  this.state.email, onChange:  this.changeEmail}), React.createElement("br", null), 
          React.createElement("input", {type: "submit", value: "Submit"})
        )
      )
    );
  }
});


var RC= React.createClass({displayName: "RC",

  onFormSubmit: function(data, callback){

     $.ajax({
        url: this.props.url,
        dataType: 'json',
        type: 'POST',
        data: data,
        success: callback,
        error: function(xhr, status, err) {

          console.error(this.props.url, status, err.toString());

        }.bind(this)
      });
  },

  render: function() {
    return React.createElement(FormComp, {onFormSubmit: this.onFormSubmit})
  }
});
var renderClient = function () {
React.render(
  React.createElement(RC, null),
  document.getElementById('content')
);
}