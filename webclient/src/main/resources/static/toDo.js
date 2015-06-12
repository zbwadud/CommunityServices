/* global mountNode */

var converter = new Showdown.converter();

var TodoList = React.createClass({displayName: "TodoList",
  render: function() {
    var createItem = function(itemText, index) {
      return React.createElement("li", {key: index + itemText}, itemText);
    };
    return React.createElement("ul", null, this.props.items.map(createItem));
  }
});

var TodoApp = React.createClass({displayName: "TodoApp",
  getInitialState: function() {
    return {items: [], text: ''};
  },
  onChange: function(e) {
    this.setState({text: e.target.value});
  },
  handleSubmit: function(e) {
    e.preventDefault();
    var nextItems = this.state.items.concat([this.state.text]);
    var nextText = '';
    this.setState({items: nextItems, text: nextText});
  },
  render: function() {
    return (
      React.createElement("div", null, 
        React.createElement("h3", null, "TODO:-"), 
        React.createElement(TodoList, {items: this.state.items}), 
        React.createElement("form", {onSubmit: this.handleSubmit}, 
          React.createElement("input", {onChange: this.onChange, value: this.state.text}), 
          React.createElement("button", null, 'Add #' + (this.state.items.length + 1))
        )
      )
    );
  }
});

//React.render(React.createElement(TodoApp, null), mountNode);

var renderClientTD = function () {
    //var data = comments || [];
    React.render(
        React.createElement(TodoApp,null),
        document.getElementById("content")
    );
};

var renderServerTD = function () {
    //var data = Java.from(comments);
    
    return React.renderToString(
        React.createElement(TodoApp, null)
    );
};