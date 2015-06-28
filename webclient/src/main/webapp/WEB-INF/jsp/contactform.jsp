<%-- 
    Document   : contactform
    Created on : Jun 28, 2015, 10:24:14 PM
    Author     : Zaid Wadud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>React Contact Form Example</title>
    <script type="text/javascript" src="vendor/react.js"></script>
    <script type="text/javascript" src="vendor/JSXTransformer.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script><!--Required for client side rendering -->

  </head>
  <body>    
      <div id="content"></div>
    <script type="text/javascript" src="contactForm.js"></script>
    <script type="text/javascript">
    $(function () {
        renderClient();
    });
</script>
  </body>
</html>


