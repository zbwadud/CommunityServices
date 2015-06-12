<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello React</title>
    <script type="text/javascript" src="vendor/react.js"></script>
        <script type="text/javascript" src="vendor/JSXTransformer.js"></script>
    <script type="text/javascript" src="vendor/showdown.min.js"></script>
    
    
</head>
<body>
<div id="content">${content}</div>
<script type="text/javascript" src="toDo.js"></script>
<script type="text/javascript">
    $(function () {
        renderClientTD();
    });
</script>
<div id="title">${title}</div>
<script type="text/javascript" src="app.js"></script>
</body>
</html>