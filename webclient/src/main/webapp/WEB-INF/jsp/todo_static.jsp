<%-- 
    Document   : todo_static
    Created on : 10/06/2015, 2:05:04 PM
    Author     : Zaid Wadud @ NZQA 2015
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todo</title>
        <script type="text/javascript" src="vendor/react.js"></script>
        <script type="text/javascript" src="vendor/JSXTransformer.js"></script>
        <!--<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>-->
    </head>
    <body>
        <div id="content"></div>
        <script type="text/jsx">
            var TodoList = React.createClass({
            render: function() {
                //alert('in render func');
            var createItem = function(itemText) {
            return <li>{itemText}</li>;
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

            React.render(
            <TodoApp />, 
            document.getElementById('content')
            );

        </script>
        coded react component......
    </body>
</html>
