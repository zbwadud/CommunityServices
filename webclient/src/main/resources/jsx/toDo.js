var converter = new Showdown.converter();

var TodoList = React.createClass({
  render: function() {
    var createItem = function(itemText, index) {
      return <li key={index + itemText}>{itemText}</li>;
    };
    return <ul>{this.props.items.map(createItem)}</ul>;
  }
});
var TodoApp = React.createClass({
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
      <div>
        <h3>TODO</h3>
        <TodoList items={this.state.items} />
        <form onSubmit={this.handleSubmit}>
          <input onChange={this.onChange} value={this.state.text} />
          <button>{'Add #' + (this.state.items.length + 1)}</button>
        </form>
      </div>
    );
  }
});

var renderClientTD = function () {
    //var data = comments || [];
    React.render(
        React.render(<TodoApp data={'datatata'} url='comments.json' pollInterval={5000} />,
        document.getElementById("content")
    );
};

var renderServerTD = function () {
    //var data = Java.from(comments);
    return React.renderToString(
        <TodoApp data={'datata'} url='comments.json' pollInterval={5000}/>
    );
};